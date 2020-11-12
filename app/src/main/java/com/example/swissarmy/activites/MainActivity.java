package com.example.swissarmy.activites;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.swissarmy.R;
import com.example.swissarmy.models.Training;
import com.orm.SugarContext;

public class MainActivity extends AppCompatActivity {

    private TextView bestView;
    private TextView latestView;
    private Training displayTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SugarContext.init(this);
        bestView = findViewById(R.id.bestView);
        latestView = findViewById(R.id.latestView);

        displayTraining = Training.findById(Training.class, 1);
        if (displayTraining == null){
            new Training((long)1, 0, 0).save();
        }
        String best = displayTraining.getBest() + "/125";
        String latest = displayTraining.getPoints() + "/125";

        bestView.setText(best);
        latestView.setText(latest);

    }

    public void openTask1(View view) {

        Intent intent = new Intent(this, Task1.class);
        startActivity(intent);
    }

    public void onTerminate() {
        SugarContext.terminate();
    }

}
