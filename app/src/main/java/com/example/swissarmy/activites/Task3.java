package com.example.swissarmy.activites;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
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
public class Task3 extends AppCompatActivity {

    private int points;
    CountUpTimer timer;
    private boolean timerRunning;
    private TextView textView3;
    private Button startButton3;
    private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task3);

        textView3 = findViewById(R.id.textView3);
        startButton3 = findViewById(R.id.startButton3);

        startButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTask3();
            }
        });

        Intent i = getIntent();
        points = i.getIntExtra("Points", 0);
    }

    /**
     * Startet den Timer
     */
    public void startTask3() {
        if (timerRunning){
            timer.cancel();
            timerRunning = false;
            startButton3.setText("Start");
        }else {
            timer = new CountUpTimer(300000, 1000) {
                public void onTickMs(long msecond) {
                    updateTimer(msecond);
                }
            };
            timer.start();
            startButton3.setText("Stop");
            timerRunning = true;
        }

    }

    /**
     * Updates timer und erstellt Notification nach 10 und 20 Sekunden.
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
        textView3.setText(displayTime);

        if (msecond / 1000 == 10 || msecond / 1000 == 20){
            try {
                Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), alarm);
                r.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Öffnet nächsten Task mit Aufgabe 4
     * @param view
     */
    public void openTask4(View view) {

        getPoints();

        Intent intent = new Intent(this, Task4.class).putExtra("Points", points);
        startActivity(intent);
    }

    /**
     * Berrechnet die Punktzahl anhand der Zeit
     */
    private void getPoints() {
        if (timerRunning){
            timer.cancel();
        }

        if (time > 10 && time < 27){
            //ungenügend
            points = points + 6;
        }else if (time > 27 && time < 40){
            //genügend
            points = points + 12;
        }
        else if (time > 40 && time < 46){
            //gut
            points = points + 15;
        }
        else if (time > 46 && time < 55){
            //sehr gut
            points = points + 2;
        }
        else if (time > 55){
            //hervorragend
            points = points + 25;
        }
    }
}
