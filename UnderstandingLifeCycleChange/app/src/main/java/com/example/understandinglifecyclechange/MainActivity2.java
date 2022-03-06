package com.example.understandinglifecyclechange;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    public static final String TAG = "_CHATHURA__" + MainActivity2.class.getSimpleName().toString() ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         
        Log.d(TAG, "onCreate Called");

    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart Called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop Called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy Called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause Called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume Called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart Called");
    }
}