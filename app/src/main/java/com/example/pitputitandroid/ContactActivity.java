package com.example.pitputitandroid;
import android.content.Intent;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pitputitandroid.Daos.ChatDao;
import com.example.pitputitandroid.DataBase.AppDB;
import com.example.pitputitandroid.adapters.ContactAdapterRV;
import com.example.pitputitandroid.databinding.ActivityMainBinding;
import com.example.pitputitandroid.entities.Chat;
import com.example.pitputitandroid.entities.LastMessage;
import com.example.pitputitandroid.entities.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;

import com.example.pitputitandroid.Daos.ChatDao;
import com.example.pitputitandroid.DataBase.AppDB;
import com.example.pitputitandroid.api.ChatAPI;
import com.example.pitputitandroid.api.UserAPI;
import com.example.pitputitandroid.entities.Chat;
import com.example.pitputitandroid.entities.Contact;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ContactActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private AppDB db;
    private ChatDao chatDao;
    private ContactAdapterRV adapter;

    private User me;
    private List<Chat> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ChatAPI chatAPI = new ChatAPI(getApplicationContext());
        UserAPI userAPI = new UserAPI(getApplicationContext());
        chatAPI.getChats(userAPI.getToken());
        adapter = new ContactAdapterRV(this);
        Activity context = this;
        chatAPI.getChatsResult().observe(this, new Observer<List<Chat>>() {
            @Override
            public void onChanged(List<Chat> chats) {
                if (chats != null) {
                    //TODO: ofir it ok take chats , enjoy:)
                    Log.d("TAG", "it succeed");
                } else {
                    context.finish();
                    Log.d("TAG", "error with token");
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

<<<<<<< HEAD
        AppCompatImageView settingButton = findViewById(R.id.imageSettings);
        settingButton.setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });

        AppCompatImageView imageBackPage = findViewById(R.id.imageBackPage);
        imageBackPage.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });



=======
>>>>>>> 47babf66916b12c1abe425aab882ba8b7cfb47b2
        RecyclerView rvContacts = findViewById(R.id.rvContacts);
        rvContacts.setHasFixedSize(true);
        final ContactAdapterRV adapter = new ContactAdapterRV(this);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

        List<Chat> contacts = new ArrayList<>();
        int resourceId = R.drawable.user;
        // Convert the drawable resource to a Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
        Chat c = new Chat();
        c.setUser(new User(bitmap, "moshe", "moshi"));
        c.setLastMessage(new LastMessage("12:00", "hello"));
        Chat c1 = new Chat();
        c1.setUser(new User(bitmap, "moshe1", "moshi1"));
        c1.setLastMessage(new LastMessage("12:01", "hello1"));
        Chat c2 = new Chat();
        c2.setUser(new User(bitmap, "moshe2", "moshi2"));
        c2.setLastMessage(new LastMessage("12:02", "hello2"));

        contacts.add(c);
        contacts.add(c1);
        contacts.add(c2);
        adapter.setContacts(contacts);




    }

    private void addMsg(Editable usernameField) {
        //somehow define chat

        Contact contact = new Contact(usernameField.toString());
        ChatAPI chatAPI = new ChatAPI(getApplicationContext());
        UserAPI userAPI = new UserAPI(getApplicationContext());
        chatAPI.addChat(userAPI.getToken(), contact);
        Activity context = this;
        chatAPI.getAddChatResult().observe(this, new Observer<Map<Integer, Chat>>() {
            @Override
            public void onChanged(Map<Integer, Chat> res) {
                int status = res.keySet().iterator().next();
                switch (status) {
                    case 401:
                        Log.d("TAG", "401");
                        context.finish();
                        break;
                    case 400:
                        Toast.makeText(getApplicationContext(), "User does not exist❗",
                                Toast.LENGTH_SHORT).show();
                        Log.d("TAG", "400");
                        break;
                    case 200:
                        //TODO: Ofir everything is OK, take chat
                        addChatToLocal(res.values().iterator().next());
                        Log.d("TAG", "200");
                        break;
                }

            }
        });
    }

    private void addChatToLocal(Chat chat) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                chatDao.insertChat(chat);
            }
        });
    }
}

