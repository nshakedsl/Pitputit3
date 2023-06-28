package com.example.pitputitandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.pitputitandroid.Daos.ChatDao;
import com.example.pitputitandroid.Daos.MessageDao;
import com.example.pitputitandroid.DataBase.AppDB;
import com.example.pitputitandroid.adapters.MessegesListAdapter;
import com.example.pitputitandroid.api.ChatAPI;
import com.example.pitputitandroid.api.UserAPI;
import com.example.pitputitandroid.entities.Chat;
import com.example.pitputitandroid.entities.Message;
import com.example.pitputitandroid.entities.Msg;
import com.example.pitputitandroid.entities.User;
import com.example.pitputitandroid.viewmodels.ChatViewModel;
import com.example.pitputitandroid.viewmodels.MessegesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ChatsActivity extends AppCompatActivity {
    private AppDB db;
    private MessageDao messageDao;
    //todo: get the current user/ delete
    private MessegesListAdapter adapter;
    private MessegesViewModel viewModel;
    private final Queue<Message> insertQueue = new LinkedList<>();
    private String chatId;
    private String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);

        Intent intent =getIntent();
        chatId = intent.getStringExtra("chatId");
        userName = intent.getStringExtra("userName");
        AppCompatImageView goBack = findViewById(R.id.btnBack);
        goBack.setOnClickListener(v -> {
            this.finish();
        });
        viewModel = new ViewModelProvider(this).get(MessegesViewModel.class);
        viewModel.getMessages().observe(this, chats -> {
            adapter.setMesseges(chats);
        });
        FrameLayout buttonFrame = findViewById(R.id.layoutSend);
        buttonFrame.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

        AppCompatImageView settingButton = findViewById(R.id.imageSettings);
        settingButton.setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });

        AppCompatImageView backButton = findViewById(R.id.btnBack);
        backButton.setOnClickListener(v -> {
            startActivity(new Intent(this, ContactActivity.class));
        });

        RecyclerView lstMesseges = findViewById(R.id.lstMesseges);
        FloatingActionButton sendButton = findViewById(R.id.btnSend);
        EditText editText = findViewById(R.id.inputMessage);
        sendButton.setOnClickListener(v -> sendMessage(editText.getText()));
        this.adapter = new MessegesListAdapter(this);
        lstMesseges.setAdapter(adapter);
        lstMesseges.setLayoutManager(new LinearLayoutManager(this));

        List<Message> messages = new ArrayList<>();

        db = AppDB.getInstance(this);
        messageDao = db.messageDao();
        UserAPI userAPI = new UserAPI(getApplicationContext());
        adapter.setMesseges(messages);

        ChatAPI chatAPI = new ChatAPI(getApplicationContext());
        chatAPI.getChatMessages(userAPI.getToken(), chatId);
        Activity context = this;
        chatAPI.getChatsMessageResult().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                if (messages != null) {
                    Log.d("TAG", "get chat messages success");
                    viewModel.set(messages);
                } else {
                    Log.d("TAG", "error");
                    //TODO: maybe clear messages?
                    context.finish();
                }
            }
        });
    }

    private boolean sendMessage(Editable message) {
        Msg msg = new Msg(message.toString());
        boolean success = true;
        ChatAPI chatAPI = new ChatAPI(getApplicationContext());
        UserAPI userAPI = new UserAPI(getApplicationContext());

        chatAPI.sendMessage(userAPI.getToken(), msg, chatId);
        Activity context = this;
        chatAPI.getSendMessageResult().observe(this, new Observer<Message>() {
            @Override
            public void onChanged(Message sentMessage) {
                if (sentMessage != null) {
                    Log.d("TAG", "messages success");
                    addMsgToLocal(sentMessage);
                } else {
                    Toast.makeText(getApplicationContext(), "error sending message",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, SettingsActivity.class);
                    intent.setAction("logout");
                    startActivity(intent);
                }
            }
        });
        message.clear();
        return success;
    }

    private void addMsgToLocal(Message msg) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            viewModel.add(msg);

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //adapter.notifyDataSetChanged();
    }
}
