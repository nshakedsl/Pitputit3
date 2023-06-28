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
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pitputitandroid.Daos.ChatDao;
import com.example.pitputitandroid.DataBase.AppDB;
import com.example.pitputitandroid.adapters.ChatListAdapter;
import com.example.pitputitandroid.databinding.ActivityMainBinding;
import com.example.pitputitandroid.entities.Chat;
import com.example.pitputitandroid.entities.User;
import com.example.pitputitandroid.viewmodels.ChatViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;

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
    private ChatListAdapter adapter;

    private User me;
    private List<Chat> contactList;
    private ChatViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ChatAPI chatAPI = new ChatAPI(getApplicationContext());
        UserAPI userAPI = new UserAPI(getApplicationContext());
        chatAPI.getChats(userAPI.getToken());
        adapter = new ChatListAdapter(this);
        Activity context = this;
        chatAPI.getChatsResult().observe(this, chats -> {
            if (chats != null) {
                viewModel.set(chats);
                Log.d("TAG", "it succeed");
            } else {
                Log.d("TAG", "error with token");
                Intent intent = new Intent(context, SettingsActivity.class);
                intent.setAction("logout");
                startActivity(intent);
            }

        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        EditText editText = findViewById(R.id.inputNickname);
        db = AppDB.getInstance(this);
        chatDao = db.chatDao();
        FloatingActionButton addBtn = findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(v -> addMsg(editText.getText()));

        AppCompatImageView settingButton = findViewById(R.id.imageSettings);
        settingButton.setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });




        RecyclerView rvContacts = findViewById(R.id.rvContacts);
        rvContacts.setHasFixedSize(true);
        final ChatListAdapter adapter = new ChatListAdapter(this);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

        // Convert the drawable resource to a Bitmap

        viewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        viewModel.getChats().observe(this, chats -> {
            adapter.setChats(chats);
        });
    }

    private void addMsg(Editable usernameField) {
        //somehow define chat

        Contact contact = new Contact(usernameField.toString());
        ChatAPI chatAPI = new ChatAPI(getApplicationContext());
        UserAPI userAPI = new UserAPI(getApplicationContext());
        chatAPI.addChat(userAPI.getToken(), contact);
        Activity context = this;
        chatAPI.getAddChatResult().observe(this, res -> {
            int status = res.keySet().iterator().next();
            switch (status) {
                case 401:
                    Log.d("TAG", "401");
                    Intent intent = new Intent(context, SettingsActivity.class);
                    intent.setAction("logout");
                    startActivity(intent);
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

        });
    }

    private void addChatToLocal(Chat chat) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> viewModel.add(chat));
    }
    @Override
    public void onResume(){
        super.onResume();
    }
}

