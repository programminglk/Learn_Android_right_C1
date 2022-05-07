package com.example.sqliteexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button b_add, b_delete , b_getAll, b_update;
    EditText et_name , et_mobile , et_email;

    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_add = findViewById(R.id.btn_add);
        b_delete = findViewById(R.id.btn_delete);
        b_getAll = findViewById(R.id.btn_getAll);
        b_update = findViewById(R.id.btn_update);

        et_name =  findViewById(R.id.f_name);
        et_email =  findViewById(R.id.f_email);
        et_mobile =  findViewById(R.id.f_mobile);

        db = new DBHelper(this);

        setClickListners();


    }

    private void setClickListners() {
        b_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String mobile = et_mobile.getText().toString().trim();

                boolean insertData_Check =  db.insertUserData(mobile,name,email);

                if(insertData_Check == true){
                    Toast.makeText(MainActivity.this, "New Friend was added to DB", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Insert Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
//
        b_getAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = db.getUserData();
                if(cursor.getCount() > 0){
                    StringBuffer buffer = new StringBuffer();
                    while (cursor.moveToNext()){
                        buffer.append("Mobile :"+ cursor.getString(0)+"\n");
                        buffer.append("Name :"+ cursor.getString(1)+"\n");
                        buffer.append("Email :"+ cursor.getString(2)+"\n\n");
                    }

                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Data From Data Base");
                    builder.setCancelable(true);
                    builder.setMessage(buffer.toString());
                    builder.show();
                }else{
                    Toast.makeText(MainActivity.this, "No Any Data in DB", Toast.LENGTH_SHORT).show();
                }
            }
        });
//
        b_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String mobile = et_mobile.getText().toString().trim();

                boolean updateData_Check =db.updateUserData(mobile,name ,email);
                if(updateData_Check){
                    Toast.makeText(MainActivity.this, "A friend was updated", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "update Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });
//
        b_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = et_mobile.getText().toString().trim();

                boolean dltData_Check =db.deleteUserData(mobile);
                if(dltData_Check){
                    Toast.makeText(MainActivity.this, "A friend was Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Delete Failed", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}