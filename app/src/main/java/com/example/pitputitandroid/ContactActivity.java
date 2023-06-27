package com.example.pitputitandroid;

import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pitputitandroid.Daos.ChatDao;
import com.example.pitputitandroid.DataBase.AppDB;
import com.example.pitputitandroid.entities.Chat;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ContactActivity extends AppCompatActivity {
    private AppDB db;
    private ChatDao chatDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        EditText editText = findViewById(R.id.inputNickname);
        db = AppDB.getInstance(this);
        chatDao = db.chatDao();
        FloatingActionButton addBtn = findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(v -> addMsg(editText.getText()));
    }
    private void addMsg(Editable nicknameFiled){
        //todo: find the relevant chat
        //somehow define chat
        Chat chat = new Chat();
        addMsgToLocal(chat);
        // TODO: Shaked 26/06/2023 add the created chat to server
    }
    private void addMsgToLocal(Chat chat) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                chatDao.insertChat(chat);
            }
        });
    }
}

