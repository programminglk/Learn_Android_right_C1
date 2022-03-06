package com.example.day4_navigatebwtweenactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityA extends AppCompatActivity {

    Button btn_toB;
    Button btn_toC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_toB = findViewById(R.id.button1_toB);
        btn_toC = findViewById(R.id.button2_toC);

        setOnClickListners() ;
    }

    private void setOnClickListners() {

        btn_toB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ActivityA.this, ActivityB.class);
                startActivity(intent1);
            }
        });


        btn_toC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityA.this, ActivityC.class);
                startActivity(i);
            }
        });

    }
}