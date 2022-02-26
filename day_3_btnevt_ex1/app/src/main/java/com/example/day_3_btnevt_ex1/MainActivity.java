package com.example.day_3_btnevt_ex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.awt.font.TextAttribute;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private EditText name_et;
    private EditText age_et;
    private EditText email_et;
    private EditText phone_et;

    private TextView tv_displayData;

    private Button btn_add;
    private Button btn_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_et = findViewById(R.id.et_name);
        age_et = findViewById(R.id.et_age);
        phone_et = findViewById(R.id.et_phone);
        email_et = findViewById(R.id.et_email);
        tv_displayData =  findViewById(R.id.tv_displayData);
        btn_clear =  findViewById(R.id.btn_clear);
        btn_add =  findViewById(R.id.btn_add);

        setClickListners();

    }

    private void setClickListners() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = name_et.getText().toString().trim();
                String email = email_et.getText().toString().trim();
                String message = "Hi "+ name + "\n" +
                                "Your email is " + email;

                Log.d(TAG, message);
                tv_displayData.setText(message);

            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_et.setText("");
                age_et.setText("");
                phone_et.setText("");
                email_et.setText("");
                tv_displayData.setText("");
            }
        });

    }
}