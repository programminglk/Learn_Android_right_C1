package com.example.myapplicationday1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    private Button mFirstBtn;
    private Button mSecondBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirstBtn = findViewById(R.id.button1);
        mSecondBtn = findViewById(R.id.button2);

        mFirstBtn.setOnClickListener(MainActivity.this);
        mSecondBtn.setOnClickListener(MainActivity.this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.button1:
                Log.d(TAG, "First Button Click");
                break;

            case R.id.button2:
                Log.d(TAG, "Second Button Click");
                break;
        }

    }
}