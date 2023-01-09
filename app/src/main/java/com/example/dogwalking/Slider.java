package com.example.dogwalking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dogwalking.databinding.ActivitySliderBinding;
import com.example.dogwalking.model.profile.User;
import com.example.dogwalking.ui.dashboard.Offer;
import com.example.dogwalking.ui.login.Login;

public class Slider extends AppCompatActivity {

    static ActivitySliderBinding binding;
    static TextView description1, dogBreed1, address1;
    static String descriptionString, dogBreedString, addressString, usernameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        binding = ActivitySliderBinding.inflate(getLayoutInflater());

        description1 = (TextView) findViewById(R.id.name_profile);
        dogBreed1 = (TextView) findViewById(R.id.country_profile);
        address1 = (TextView) findViewById(R.id.phone_profile);

        User user = MainActivity.getUser(usernameString);
        Offer offer = MainActivity.getOffer(usernameString);
        description1.setText(offer.getDescription());
        address1.setText(user.getPhone());
        dogBreed1.setText(dogBreedString);

        Button chatButton = (Button) findViewById(R.id.openChatButton);
        if (!Login.et_username.getText().toString().equals(usernameString) )
            chatButton.setVisibility(View.VISIBLE);
        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.insertContact("last", Login.et_username.getText().toString(), usernameString);
                finish();
            }
        });
        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        if (Login.et_username.getText().toString().equals(usernameString) ) {
            deleteButton.setVisibility(View.VISIBLE);
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.deleteOffer(usernameString);
               finish();
            }
        });


    }

    public static void saveFields(String description, String address, String dogBreed, String username){

        usernameString = username;
        descriptionString = description;
        addressString = address;
        dogBreedString = dogBreed;
    }


}