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
import com.example.swissarmy.models.Training;
import com.orm.SugarContext;
import com.orm.SugarRecord;

public class Task5 extends AppCompatActivity {

    private int points;
    CountUpTimer timer;
    private boolean timerRunning;
    private int time;
    private TextView textView5;
    private Button startButton5;
    int i = 0;
    long alarmMsec = 42;
    long increase =  42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task5);

        textView5 = findViewById(R.id.textView5);
        startButton5 = findViewById(R.id.startButton5);

        startButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTask5();
            }
        });

        Intent i = getIntent();
        points = i.getIntExtra("Points", 0);
    }

    public void startTask5() {
        if (timerRunning){
            timer.cancel();
            timerRunning = false;
            startButton5.setText("Start");
        }else {
            startAlarm();
            timer = new CountUpTimer(3000000, 100) {
                public void onTickMs(long msecond) {
                    updateTimer(msecond);
                }
            };
            timer.start();
            startButton5.setText("Stop");
            timerRunning = true;
        }
    }

    private void updateTimer(long msecond) {
        time = (int) msecond/1000;
        int min = (int) msecond / 60000;
        int sec = (int) msecond % 60000 / 1000;

        String displayTime;
        displayTime = "" + min;
        displayTime += ":";
        if (sec < 10){
            displayTime += "0";
        }
        displayTime += sec;
        textView5.setText(displayTime);

        if (i <= 19) {
            if (msecond / 100 == alarmMsec) {
                i = i++;
                try {
                    Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), alarm);
                    r.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                alarmMsec = alarmMsec + increase;
            }
        }else {
            increase = increase - 2;
            i = 0;
        }
    }

    public void openHome(View view) {

        getPoints();

        Training training = Training.findById(Training.class, 1);
        Training saveTraining = new Training();

        if (training.getBest() < points){
            saveTraining.setId((long) 1);
            saveTraining.setBest(points);
            saveTraining.setPoints(points);
        }else {
            saveTraining.setId((long)1);
            saveTraining.setBest(training.getBest());
            saveTraining.setPoints(points);
        }

        saveTraining.update();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void getPoints() {
        if (timerRunning){
            timer.cancel();
        }

        if (time >= 60 && time < 320){
            //ungenügend
            points = points + 6;
        }else if (time > 320 && time < 630){
            //genügend
            points = points + 12;
        }
        else if (time > 630 && time < 810){
            //gut
            points = points + 15;
        }
        else if (time > 810 && time < 980){
            //sehr gut
            points = points + 2;
        }
        else if (time > 980){
            //hervorragend
            points = points + 25;
        }
    }

    private void startAlarm() {
        //TODO: ALL 4,2 seconds alarm and that 20 times. Then 4,0seconds 20 times etc.
    }
}
