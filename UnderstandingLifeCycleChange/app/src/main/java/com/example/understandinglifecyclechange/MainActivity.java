package com.example.understandinglifecyclechange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "_CHATHURA__" + MainActivity.class.getSimpleName().toString() ;

    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "On Create Called");


        b1=findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });
    }

    public MainActivity() {
        super();
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