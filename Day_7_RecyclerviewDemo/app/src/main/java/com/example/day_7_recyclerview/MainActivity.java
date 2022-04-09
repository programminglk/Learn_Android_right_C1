package com.example.day_7_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.day_7_recyclerview.data.Doctor;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et_name;
    EditText et_email;
    Button btn_addtoList;
    Button btn_clear;
    Button btn_viewList;

    RecyclerView rv_doctors;
    RecyclerView.LayoutManager layoutManager;
    DoctorsAdpter adapter;


    ArrayList<Doctor> doctorsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        btn_addtoList =  findViewById(R.id.btn_add);
        btn_clear =  findViewById(R.id.btn_clear);

        rv_doctors = findViewById(R.id.rv_doctorList);
        layoutManager = new LinearLayoutManager(this);
        adapter = new DoctorsAdpter(doctorsList);
        rv_doctors.setLayoutManager(layoutManager);
        rv_doctors.setAdapter(adapter);

        initView();

    }

    private void initView() {
        btn_addtoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Doctor doctor = new Doctor();

                    String name = et_name.getText().toString();
                    String email = et_email.getText().toString();

                    doctor.setEmail(email);
                    doctor.setName(name);

                    doctorsList.add(doctor);
                    adapter.notifyDataSetChanged();

            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_email.setText("");
                et_name.setText("");
            }
        });


    }
}