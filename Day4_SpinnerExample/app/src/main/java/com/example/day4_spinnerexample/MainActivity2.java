package com.example.day4_spinnerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView colorTv;
    TextView foodTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();

        String color =  intent.getStringExtra("COLOR");
        String food =  intent.getStringExtra("FOOD");

        colorTv = findViewById(R.id.tv_color);
        foodTv = findViewById(R.id.tv_food);

        colorTv.setText(color);
        foodTv.setText(food);


    }
}