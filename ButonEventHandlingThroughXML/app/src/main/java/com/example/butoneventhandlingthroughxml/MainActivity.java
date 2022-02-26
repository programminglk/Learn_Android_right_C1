package com.example.butoneventhandlingthroughxml;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void performSomeAction(View view) {

        switch (view.getId()){
            case R.id.button1:
                Log.d(TAG , "Button 1 Click");
                break;

            case R.id.button2:
                Log.d(TAG , "Button 2 Click");
                break;
        }

    }

}