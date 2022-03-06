package com.example.day4_spinnerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

public class EntryActivity extends AppCompatActivity {

    String SelectedFood="";
    String SelectedColor="";

    Spinner spinnerColor;
    Spinner spinnerFood;
//
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerColor = findViewById(R.id.spinner_color);
        spinnerFood = findViewById(R.id.spinner_food);
        button = findViewById(R.id.button);



        spinnerColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SelectedColor = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SelectedColor = adapterView.getSelectedItem().toString();
            }
        });

        spinnerFood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SelectedFood = adapterView.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SelectedFood = adapterView.getSelectedItem().toString();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EntryActivity.this, MainActivity2.class);
                i.putExtra("COLOR", SelectedColor);
                i.putExtra("FOOD", SelectedFood);
                startActivity(i);
            }
        });


    }






}