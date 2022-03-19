package com.example.day5_fragments2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button_A;
    Button button_B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_A = findViewById(R.id.button1);
        button_B = findViewById(R.id.button2);

        setOnClickListners();

    }

    private void setOnClickListners() {

        button_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragmentA();
            }
        });

        button_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragmentB();
            }
        });

    }

    private void openFragmentB() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FragmentB fragmentB = new FragmentB();

        fragmentTransaction.add(R.id.frg_container_b,fragmentB );
        fragmentTransaction.commit();


    }

    private void openFragmentA() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentA fragmentA = new FragmentA();
        fragmentTransaction.add(R.id.frg_container_a,fragmentA );
        fragmentTransaction.commit();
    }


}