package com.example.day4_navigatebwtweenactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityC extends AppCompatActivity {
    Button btn_toA;
    Button btn_toB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        btn_toA = findViewById(R.id.button2_toA);
        btn_toB = findViewById(R.id.button1_toB);

        setOnClickListners() ;

    }

    private void setOnClickListners() {

        btn_toA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityC.this, ActivityA.class);
                startActivity(i);
            }
        });

        btn_toB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ActivityC.this, ActivityB.class);
                startActivity(i);
            }
        });

    }
}