package com.example.swissarmy.activites;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.swissarmy.R;

/**
 * @author Jannis Thaler
 * @version 1.0.0
 * @date 16.11.2020
 */
public class Task1 extends AppCompatActivity {

    private int points;
    private EditText textTask1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);

        textTask1 = (EditText) findViewById(R.id.textTask1);
    }

    /**
     * Öffnet nächsten Task mit Aufgabe 2
     * @param view
     */
    public void openTask2(View view) {
        double value;

        if (textTask1.getText().toString().isEmpty()){
            value = 0;
        }else{
            value = Double.parseDouble(textTask1.getText().toString());
        }

        getPoints(value);

        Intent intent = new Intent(this, Task2.class).putExtra("Points", points);
        startActivity(intent);
    }

    /**
     * Berrechnet die Punktzahl anhand der Meter
     */
    public void getPoints(double value) {
        if (value > 1 && value < 1.95){
            //ungenügend
            points = points + 6;
        }else if (value > 1.95 && value < 2.25){
            //genügend
            points = points + 12;
        }
        else if (value > 2.25 && value < 2.40){
            //gut
            points = points + 15;
        }
        else if (value > 2.40 && value < 2.55){
            //sehr gut
            points = points + 2;
        }
        else if (value > 2.55){
            //hervorragend
            points = points + 25;
        }
    }
}
