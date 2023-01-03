package com.example.dogwalking.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dogwalking.RegisterActivity;
import com.example.dogwalking.model.profile.ProfileDbHelper;
import com.example.dogwalking.MainActivity;
import com.example.dogwalking.R;


public class Login extends AppCompatActivity {

    public static EditText et_username;
    static EditText et_password;
    Button btn_login, btn_confirmation, btn_register;
    private ProfileDbHelper dbHelper;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println();
        System.out.println();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new ProfileDbHelper(this);

        register();
        login();
    }

   void login(){
           et_username = (EditText)findViewById(R.id.et_username);
           et_password = (EditText)findViewById(R.id.et_password);
           btn_login = (Button)findViewById(R.id.btn_login);

    btn_login.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {
        username = et_username.getText().toString();
        password = et_password.getText().toString();
        if (dbHelper.checkLogin(username, password)){
                Toast.makeText(Login.this, "Username and Password is correct", Toast.LENGTH_SHORT).show();
                openMainActivity();

            }
            else{
                Toast.makeText(Login.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }

    public void register(){
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }

        });
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    /*this.btn_confirmation.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "Date Picker");
        }
    });*/

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openRegisterActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


    
}
