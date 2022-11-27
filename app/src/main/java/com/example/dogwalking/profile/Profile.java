package com.example.dogwalking.profile;

import android.content.Intent;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dogwalking.R;
import com.example.dogwalking.ui.login.Login;

public class Profile extends AppCompatActivity {

    public static final class ProfileEntry implements BaseColumns{
        public static final String TABLE_NAME = "Users";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_EMAIL = "email";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
        Button logout_button = (Button) findViewById(R.id.logout_button);

        logout_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openNewActivity();
            }

        });
    }


    public void openNewActivity(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }




}
