package com.example.programminglknotebook;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.programminglknotebook.db.DBHelper;

public class LoginSignUpActivity extends AppCompatActivity {

    ProgressDialog progressDialog;

    RelativeLayout loginView;
    RelativeLayout signupView;
    Button login_btn;
    Button signup_btn;
    Button signup_login;

    EditText signup_userName;
    EditText signup_pwd1;
    EditText signup_pwd2;

    EditText login_userName;
    EditText login_pwd;


    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        db = new DBHelper(this);


        initUI();
        setOnClickListners();

        Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        loginView.startAnimation(slide_up);
        loginView.setVisibility(View.VISIBLE);
    }

    private void setOnClickListners() {
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation slide_up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
                signupView.startAnimation(slide_up);
                signupView.setVisibility(View.VISIBLE);
            }
        });

        signup_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate_insertUserData();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticateLoginToHomePage();
            }
        });

    }

    private void authenticateLoginToHomePage() {
        String userName = login_userName.getText().toString().trim();
        String pwd = login_pwd.getText().toString().trim();

        AuthenticateAsyncTask authenticateAsyncTask = new AuthenticateAsyncTask();
        authenticateAsyncTask.execute(userName,pwd );



    }

    private boolean validate_insertUserData() {
        boolean inputsValidated = false;
        boolean insertUserSuccess = false;

        String userName = signup_userName.getText().toString().trim();
        String pwd1 = signup_pwd1.getText().toString().trim();
        String pwd2 = signup_pwd2.getText().toString().trim();

        if (userName.isEmpty() || pwd1.isEmpty() || pwd2.isEmpty()) {
            showMessage("Error", "Some required fields are empty!");
            inputsValidated = false;
        } else if (pwd1.equals(pwd2)) {
            inputsValidated = true;
        } else {
            showMessage("Error", "Entered passwords are not equal!");
            inputsValidated = false;
        }

        if (inputsValidated) {
            //insertUserSuccess = db.insert_UserData(userName, pwd1);
            InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
            insertAsyncTask.execute(userName,pwd1);
        }

        return insertUserSuccess;

    }

    private void showMessage(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(LoginSignUpActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void initUI() {
        loginView = findViewById(R.id.loginView);
        signupView = findViewById(R.id.signup_view);
        login_btn = findViewById(R.id.btn_login);
        signup_btn = findViewById(R.id.btn_signup);
        signup_login = findViewById(R.id.btn_login1);

        signup_userName = findViewById(R.id.et_userName1);
        signup_pwd1 = findViewById(R.id.et_password1);
        signup_pwd2 = findViewById(R.id.et_confirmpassword1);

        login_userName = findViewById(R.id.et_userName);
        login_pwd = findViewById(R.id.et_password);


    }

    private class InsertAsyncTask extends AsyncTask<String,String,Boolean>{

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(LoginSignUpActivity.this);
            progressDialog.setMessage("Please Wait.. Signing Up");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected Boolean doInBackground(String... strings) {
            boolean isertUserSuccess = false;
            try {
                isertUserSuccess =  db.insert_UserData(strings[0], strings[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return isertUserSuccess;
        }
        @Override
        protected void onPostExecute(Boolean isertUserSuccess) {
            super.onPostExecute(isertUserSuccess);
            progressDialog.hide();
            if(isertUserSuccess) {
                showMessage("Signup Success"," Successfully Signed up!");
                Animation slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.slide_down);
                signupView.startAnimation(slide_down);
                signupView.setVisibility(View.GONE);
            }else{
                showMessage("Error"," Signup Failed!");
            }
        }
    }

    private class AuthenticateAsyncTask extends AsyncTask<String,String,Cursor>{

        String pwd = "";
        String uName = "";
        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(LoginSignUpActivity.this);
            progressDialog.setMessage("Please Wait.. Logging in");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
        @Override
        protected Cursor doInBackground(String... strings) {
            pwd = strings[1];
            uName = strings[0];
            Cursor cursor = null;
            try {
               cursor = db.getUserDataByName(uName) ;
            }catch (Exception e){
                e.printStackTrace();
            }
            return cursor;
        }
        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            progressDialog.hide();
            if(cursor.getCount() > 0){
                while (cursor.moveToNext()){
                    if(cursor.getString(1).equals(pwd)){
                        openHomeActivity(uName, pwd);
                    }else{
                        showMessage("Login Failed", "Password not matching!");
                    }
                }
            }else{
                showMessage("Login Failed", "No user Found");
            }
        }
    }

    private void openHomeActivity(String uName, String pwd) {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("USER_NAME", uName);
        startActivity(intent);
        finish();
    }

}