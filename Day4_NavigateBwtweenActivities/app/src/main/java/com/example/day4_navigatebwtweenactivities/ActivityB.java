package com.example.day4_navigatebwtweenactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityB extends AppCompatActivity {

    Button btn_toA;
    Button btn_toC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        btn_toA = findViewById(R.id.button1_toA);
        btn_toC = findViewById(R.id.button2_toC);

        setOnClickListners() ;

    }

    private void setOnClickListners() {

        btn_toA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityB.this, ActivityA.class);
                startActivity(i);
            }
        });

        btn_toC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityB.this, ActivityC.class);
                startActivity(i);
            }
        });

    }
}