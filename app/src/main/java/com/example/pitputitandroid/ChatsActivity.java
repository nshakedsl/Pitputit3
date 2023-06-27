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
    private User me;
    private MessegesListAdapter adapter;
    private List<Message> messageList;
    Queue<Message> insertQueue = new LinkedList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);


        FrameLayout buttonFrame = findViewById(R.id.layoutSend);
        buttonFrame.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

        AppCompatImageView settingButton = findViewById(R.id.imageSettings);
        settingButton.setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });


        RecyclerView lstMesseges = findViewById(R.id.lstMesseges);
        FloatingActionButton sendButton = findViewById(R.id.btnSend);
        EditText editText = findViewById(R.id.inputMessage);
        sendButton.setOnClickListener(v -> sendMessage(editText.getText()));
        this.adapter = new MessegesListAdapter(this);
        lstMesseges.setAdapter(adapter);
        lstMesseges.setLayoutManager(new LinearLayoutManager(this));

        // Get the resource ID of the drawable
        int resourceId = R.drawable.user;

        // Convert the drawable resource to a Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);

        List<Message> messages = new ArrayList<>();

        db = AppDB.getInstance(this);
        messageDao = db.messageDao();
        UserAPI userAPI = new UserAPI(getApplicationContext());
        User moshe = new User(userAPI.getProfilePic(),userAPI.getUsername(),userAPI.getDisplayName());
        //todo: kill hardcoded user
        this.me = moshe;
        Message msg = new Message("hello everyone!!", moshe, "12:00");

        adapter.setMesseges(messages);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    private boolean sendMessage(Editable message) {
        Msg msg=new Msg(message.toString());
        //addMsgToLocal(createdMessage);
        boolean success = true;
        ChatAPI chatAPI = new ChatAPI(getApplicationContext());
        UserAPI userAPI = new UserAPI(getApplicationContext());
        //TODO: change the hard_coded id
        chatAPI.sendMessage(userAPI.getToken(),msg,"649affa8d0dd5fa489ff6e35");
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
                    context.finish();
                }
            }
        });
        message.clear();
        return success;
    }

    private void addMsgToLocal(Message msg) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                messageDao.insertMessage(msg);
                insertQueue.add(msg);
                getMegssagesLocal();
            }
        });
    }

    private void getMegssagesLocal() {
        // Inside your activity or fragment
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                while (!insertQueue.isEmpty()){
                    adapter.getMesseges().add(insertQueue.remove());
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getMegssagesLocal();
        adapter.notifyDataSetChanged();
    }
}
