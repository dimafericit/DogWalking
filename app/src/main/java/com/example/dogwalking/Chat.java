package com.example.dogwalking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.dogwalking.model.profile.Message;
import com.example.dogwalking.model.profile.dog.ContactAdapter;
import com.example.dogwalking.model.profile.messageAdapter;
import com.example.dogwalking.ui.login.Login;

import java.util.ArrayList;

public class Chat extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        final ArrayList<Message> msgData = new ArrayList<>();
        final messageAdapter msgAdapter = new messageAdapter(Chat.this, ContactAdapter.getChat() );
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.msg_recyclerview);
        recyclerView.setAdapter(msgAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageView send = (ImageView) findViewById(R.id.send_msg_btn);
        send.setClickable(true);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText typing_space = (EditText) findViewById(R.id.typing_space);
                MainActivity.insertMessage(typing_space.getText().toString(), Login.et_username.getText().toString(), ContactAdapter.getChat(), 0);
                finish();
                startActivity(getIntent());
            }
        });
    }
}