package com.example.day_7_recyclerview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        btn_addtoList = findViewById(R.id.btn_add);
        btn_clear = findViewById(R.id.btn_clear);

        rv_doctors = findViewById(R.id.rv_doctorList);
        layoutManager = new LinearLayoutManager(this);
        adapter = new DoctorsAdpter(doctorsList);
        rv_doctors.setLayoutManager(layoutManager);
        rv_doctors.setAdapter(adapter);

        adapter.setOnDoctorItemClickListner(new DoctorsAdpter.OnDoctorClickListner() {
            @Override
            public void onDoctorItemClick(int position) {
                Doctor selectedDoctor = doctorsList.get(position);
                String name = selectedDoctor.getName();
                String email = selectedDoctor.getEmail();

                Toast.makeText(MainActivity.this, "Name :" + name + " Email:" + email, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSendEmailClick(int position) {
                Intent intent = new Intent(
                        Intent.ACTION_SENDTO,
                        Uri.fromParts("mailto", doctorsList.get(position).getEmail(), null)
                );
                intent.putExtra(Intent.EXTRA_SUBJECT, "New Year Wish!");
                intent.putExtra(Intent.EXTRA_TEXT, "Hi Doctor , happy New Year");
                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
            }

//            @Override
//            public void onSendEmailCli(int position) { // **6
//                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
//                        "mailto",doctorsList.get(position).getEmail(), null));
//                intent.putExtra(Intent.EXTRA_SUBJECT, "New Year Wish!");
//                intent.putExtra(Intent.EXTRA_TEXT, "Hi Doctor , happy New Year");
//                startActivity(Intent.createChooser(intent, "Choose an Email client :"));
//            }
        });

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