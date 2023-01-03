package com.example.dogwalking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.dogwalking.model.profile.ProfileDbHelper;
import com.example.dogwalking.ui.login.Login;

public class PopUpActivity extends AppCompatActivity {

    private ProfileDbHelper profileDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        /*EditText editUsername = (EditText) findViewById(R.id.editTextUserName);
        String username = editUsername.getText().toString();*/

        EditText editBio = (EditText) findViewById(R.id.editTextBio);
        String bio = editBio.getText().toString();


        ImageButton close_btn = (ImageButton) findViewById(R.id.closeImage);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button save_btn = (Button) findViewById(R.id.saveBtnPopUp);
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editBio = (EditText) findViewById(R.id.editTextBio);
                String bio = editBio.getText().toString();

                System.out.println(bio);
                System.out.println(Login.et_username.getText().toString());
                profileDbHelper.updateBio(Login.et_username.getText().toString(), bio);
                //profileDbHelper.updateUsername(Login.et_username.getText().toString(), username);
                finish();
            }
        });

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout( (int)(width*.8), (int)(height*.7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -29;

        getWindow().setAttributes(params);
    }

    public void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}