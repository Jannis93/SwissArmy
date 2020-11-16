package com.example.swissarmy.activites;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.swissarmy.R;
import com.orm.SugarContext;

/**
 * @author Jannis Thaler
 * @version 1.0.0
 * @date 16.11.2020
 */
public class Task2 extends AppCompatActivity {

    private int points;
    private EditText textTask2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);

        textTask2 = (EditText) findViewById(R.id.textTask2);

        Intent i = getIntent();
        points = i.getIntExtra("Points", 0);
    }

    /**
     * Öffnet nächsten Task mit Aufgabe 3
     * @param view
     */
    public void openTask3(View view) {
        double value;

        if (textTask2.getText().toString().isEmpty()){
            value = 0;
        }else{
            value = Double.parseDouble(textTask2.getText().toString());
        }

        getPoints(value);

        Intent intent = new Intent(this, Task3.class).putExtra("Points", points);
        startActivity(intent);
    }

    /**
     * Berrechnet die Punktzahl anhand der Meter
     */
    private void getPoints(double value) {
        if (value > 4 && value < 4.80){
            //ungenügend
            points = points + 6;
        }else if (value > 4.80 && value < 6){
            //genügend
            points = points + 12;
        }
        else if (value > 6 && value < 6.60){
            //gut
            points = points + 15;
        }
        else if (value > 6.60 && value < 7.40){
            //sehr gut
            points = points + 2;
        }
        else if (value > 7.40){
            //hervorragend
            points = points + 25;
        }
    }
}
