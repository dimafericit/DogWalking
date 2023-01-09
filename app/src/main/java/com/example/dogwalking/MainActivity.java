package com.example.dogwalking;

import android.database.CursorWindow;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.example.dogwalking.databinding.ActivityMainBinding;
import com.example.dogwalking.model.profile.Contact;
import com.example.dogwalking.model.profile.Message;
import com.example.dogwalking.model.profile.ProfileDbHelper;
import com.example.dogwalking.model.profile.User;
import com.example.dogwalking.ui.dashboard.ListAdapter;
import com.example.dogwalking.ui.dashboard.Offer;
import com.example.dogwalking.ui.login.Login;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static ProfileDbHelper dbHelper;
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new ProfileDbHelper(this);
        mDatabase = dbHelper.getWritableDatabase();


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        try {
            Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); //the 100MB is the new size
        } catch (Exception e) {
            e.printStackTrace();
        }



        //Navigation Bar
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public static void publishOffer(String description, String address, String dogBreed){
        System.out.println(description);
        dbHelper.insertOffer(description, String.valueOf(Login.et_username.getText()), address, dogBreed);
    }

    public static ArrayList<Offer> getOffers(){
        return dbHelper.offersList();
    }

    public static void updateImage(byte[] image){
        dbHelper.updateImage(String.valueOf(Login.et_username.getText()), image);
    }

    public static User getUser(String username){
        return dbHelper.getUser(username);
    }

    public static void updatePhone(String username, String phone){
        dbHelper.updatePhone(username,phone);
    }

    public static void deleteOffer(String username){
        dbHelper.deleteOffer(username);
    }

    public static Offer getOffer(String username){
        return dbHelper.getOffer(username);
    }

    public static byte[] getImage(String username){
        return dbHelper.getImage(username);
    }

    public static void insertMessage(String text, String sender, String receiver, Integer seen){
        dbHelper.insertMessage(text, sender, receiver, seen);
    }

    public static ArrayList<Message> getMessages(String username, String second){
        return dbHelper.messagesList(username, second);
    }

    public static void insertContact(String recentMessage, String sender, String receiver){
        dbHelper.insertContact(recentMessage, sender, receiver);
    }

    public static  ArrayList<Contact> getContacts(String username){
        return dbHelper.contactsList(username);
    }

    public static void updateBio(String username, String bio){
        dbHelper.updateBio(username,bio);
    }



}