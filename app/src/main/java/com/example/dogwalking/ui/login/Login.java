package com.example.dogwalking.ui.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.dogwalking.DbHelper;
import com.example.dogwalking.MainActivity;
import com.example.dogwalking.R;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Login extends AppCompatActivity {

    EditText et_username, et_password;
    Button btn_login, btn_confirmation;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login();
    }

   void login(){
           et_username = (EditText)findViewById(R.id.et_username);
           et_password = (EditText)findViewById(R.id.et_password);
           btn_login = (Button)findViewById(R.id.btn_login);
           btn_confirmation = (Button) findViewById(R.id.confirmation_btn);

           btn_login.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View v) {
        if(et_username.getText().toString().equals("admin") && et_password.getText().toString().equals("admin")){
            /*if (dbHelper.checkLogin(et_username.getText().toString(), et_password.getText().toString())){*/
                Toast.makeText(Login.this, "Username and Password is correct", Toast.LENGTH_SHORT).show();


                /*Fragment fragment = new Confirmation();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, fragment);
                transaction.addToBackStack(null);
                transaction.commit();*/
            
                Intent intent = new Intent(Login.this, User.class);
                startActivity(intent);
                openNewActivity();

            }
            else{
                Toast.makeText(Login.this, "Username or Password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            });
    }

    /*this.btn_confirmation.setOnClickListener(new View.OnClickListener() {
        public void onClick(View view) {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "Date Picker");
        }
    });*/

    public void openNewActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    
}
