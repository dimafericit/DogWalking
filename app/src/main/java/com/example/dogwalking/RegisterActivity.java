package com.example.dogwalking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dogwalking.model.profile.ProfileDbHelper;
import com.example.dogwalking.ui.login.Login;

public class RegisterActivity extends AppCompatActivity {

    private ProfileDbHelper profileDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        profileDbHelper = new ProfileDbHelper(this);

        EditText email = (EditText) findViewById(R.id.editTextEmail);
        EditText password = (EditText) findViewById(R.id.editTextPassword);
        EditText username = (EditText) findViewById(R.id.editTextUsername);

        Button button = (Button) findViewById(R.id.button_register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameString = username.getText().toString();
                String emailString = email.getText().toString();
                String passwordString = password.getText().toString();

                if (passwordString.length() >= 1) {
                    if (profileDbHelper.insertUser(usernameString, passwordString, emailString))
                        openLoginActivity();
                    else
                        Toast.makeText(RegisterActivity.this, "incorrect Data", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(RegisterActivity.this, "Password must have 8 characters", Toast.LENGTH_SHORT).show();

            }
        });



    }

    public void openLoginActivity(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}