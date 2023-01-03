package com.example.dogwalking;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dogwalking.databinding.ActivityMainBinding;
import com.example.dogwalking.model.profile.ProfileDbHelper;
import com.example.dogwalking.ui.login.Login;
import com.example.dogwalking.ui.profile.ProfileFragment;

public class EditProfileActivity extends AppCompatActivity {

    private ProfileDbHelper profileDbHelper;
    private ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);



        profileDbHelper = new ProfileDbHelper(this);
        profileFragment = new ProfileFragment();

        EditText editUsername = (EditText) findViewById(R.id.et_editUsername);
        String username = editUsername.getText().toString();

        EditText editBio = (EditText) findViewById(R.id.et_editBio);
        String bio = editUsername.getText().toString();

        System.out.println();
        System.out.println();
        System.out.println(username);
        System.out.println(bio);
        Button saveButton = (Button) findViewById(R.id.btn_save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println();
                System.out.println();
                System.out.println(username);
                System.out.println(bio);
                profileDbHelper.updateBio(Login.et_username.getText().toString(), bio);
                profileDbHelper.updateUsername(Login.et_username.getText().toString(), username);

            }
        });
    }


}