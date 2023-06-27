package com.example.pitputitandroid;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.util.Pair;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.pitputitandroid.Daos.ChatDao;
import com.example.pitputitandroid.DataBase.AppDB;
import com.example.pitputitandroid.api.ChatAPI;
import com.example.pitputitandroid.api.UserAPI;
import com.example.pitputitandroid.entities.Chat;
import com.example.pitputitandroid.entities.Contact;
import com.example.pitputitandroid.entities.Message;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ContactActivity extends AppCompatActivity {
    private AppDB db;
    private ChatDao chatDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ChatAPI chatAPI = new ChatAPI(getApplicationContext());
        UserAPI userAPI = new UserAPI(getApplicationContext());
        chatAPI.getChats(userAPI.getToken());
        chatAPI.getChatsResult().observe(this, new Observer<List<Chat>>() {
            @Override
            public void onChanged(List<Chat> chats) {
               if(chats!=null){
                   //TODO: ofir it ok take chats , enjoy:)
                   Log.d("TAG","it succeed");
               }
               else {
                   //TODO: ofir it is not OK go to login :(
                   Log.d("TAG","error with token");
               }

            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        EditText editText = findViewById(R.id.inputNickname);
        db = AppDB.getInstance(this);
        chatDao = db.chatDao();
        FloatingActionButton addBtn = findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(v -> addMsg(editText.getText()));
    }
    private void addMsg(Editable usernameField){
        //somehow define chat

        Contact contact=new Contact(usernameField.toString());
        ChatAPI chatAPI = new ChatAPI(getApplicationContext());
        UserAPI userAPI = new UserAPI(getApplicationContext());
        chatAPI.addChat(userAPI.getToken(),contact);

        chatAPI.getAddChatResult().observe(this, new Observer<Map<Integer, Chat>>() {
            @Override
            public void onChanged(Map<Integer, Chat> res) {
                int status=res.keySet().iterator().next();
                switch (status) {
                    case 401:
                        Log.d("TAG", "401");
                        //TODO: Ofir error with token go to login
                        break;
                    case 400:
                        //TODO: Ofir display error to user : User does not exist❗
                        Log.d("TAG", "400");
                        break;
                    case 200:
                        //TODO: Ofir everything is OK, take chat
                        addMsgToLocal(res.values().iterator().next());
                        Log.d("TAG", "200");
                        break;
                }

            }
        });
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

