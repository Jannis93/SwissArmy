package com.example.swissarmy.activites;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.swissarmy.R;
import com.example.swissarmy.models.CountUpTimer;

/**
 * @author Jannis Thaler
 * @version 1.0.0
 * @date 16.11.2020
 */
public class Task4 extends AppCompatActivity {

    private int points;
    CountUpTimer timer;
    private boolean timerRunning;
    private int time;
    private TextView textView4;
    private Button startButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task4);

        textView4 = findViewById(R.id.textView4);
        startButton4 = findViewById(R.id.startButton4);

        startButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTask4();
            }
        });

        Intent i = getIntent();
        points = i.getIntExtra("Points", 0);

    }

    /**
     * Startet den Timer
     */
    public void startTask4() {
        if (timerRunning){
            timer.cancel();
            timerRunning = false;
            startButton4.setText("Start");
        }else {
            timer = new CountUpTimer(300000, 1000) {
                public void onTickMs(long msecond) {
                    updateTimer(msecond);
                }
            };
            timer.start();
            startButton4.setText("Stop");
            timerRunning = true;
        }
    }

    /**
     * Updates timer
     * @param msecond
     */
    private void updateTimer(long msecond) {
        time = (int) msecond / 1000;
        int min = (int) msecond / 60000;
        int sec = (int) msecond % 60000 / 1000;

        String displayTime;
        displayTime = "" + min;
        displayTime += ":";
        if (sec < 10){
            displayTime += "0";
        }
        displayTime += sec;
        textView4.setText(displayTime);
    }

    /**
     * Öffnet nächsten Task mit Aufgabe 5
     * @param view
     */
    public void openTask5(View view) {

        getPoints();

        Intent intent = new Intent(this, Task5.class).putExtra("Points", points);
        startActivity(intent);
    }

    /**
     * Berrechnet die Punktzahl anhand der Zeit
     */
    private void getPoints() {
        if (timerRunning){
            timer.cancel();
        }

        if (time > 5 && time < 35){
            //ungenügend
            points = points + 6;
        }else if (time > 35 && time < 95){
            //genügend
            points = points + 12;
        }
        else if (time > 95 && time < 125){
            //gut
            points = points + 15;
        }
        else if (time > 125 && time < 185){
            //sehr gut
            points = points + 2;
        }
        else if (time > 185){
            //hervorragend
            points = points + 25;
        }
    }

}
