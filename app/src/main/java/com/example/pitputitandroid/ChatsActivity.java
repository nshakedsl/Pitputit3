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
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.pitputitandroid.Daos.ChatDao;
import com.example.pitputitandroid.Daos.MessageDao;
import com.example.pitputitandroid.DataBase.AppDB;
import com.example.pitputitandroid.adapters.MessegesListAdapter;
import com.example.pitputitandroid.entities.Chat;
import com.example.pitputitandroid.entities.Message;
import com.example.pitputitandroid.entities.User;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ChatsActivity extends AppCompatActivity {
    private AppDB db;
    private MessageDao messageDao;
    //todo: get the current user/ delete
    private User me;
    private MessegesListAdapter adapter;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);


        FrameLayout buttonFrame=findViewById(R.id.layoutSend);
        buttonFrame.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

        AppCompatImageView settingButton = findViewById(R.id.imageSettings);
        settingButton.setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });


        RecyclerView lstMesseges = findViewById(R.id.lstMesseges);
        AppCompatImageView sendButton = findViewById(R.id.sendMessageButton);
        EditText editText = findViewById(R.id.inputMessage);
        sendButton.setOnLongClickListener(v -> sendMessage(editText.getText()));
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
        User moshe = new User(bitmap, "moshe", "mosh_nick");
        //todo: kill hardcoded user
        this.me = moshe;
        messages.add(new Message("hello everyone!!", moshe, "12:00"));
        messages.add(new Message("hello this is ", moshe, "12:00"));
        messages.add(new Message("hello world", moshe, "12:00"));


        messages.add(new Message("hello everyone!!", "aa", "mosh_nick", bitmap, "12:00" ));
        messages.add(new Message("hello this is ", "aa1", "mosh_nick", bitmap, "12:00" ));
        messages.add(new Message("hello world", "moshe2", "mosh_nick", bitmap, "12:00" ));
        messages.add(new Message("hello everyone!!", "moshe3", "mosh_nick", bitmap, "12:00" ));
        messages.add(new Message("hello this is ", "aa4", "mosh_nick", bitmap, "12:00" ));
        //addMsgToLocal(msg);

        adapter.setMesseges(messages);
//        ImageView viewBackground = findViewById(R.id.viewBackground);
//        ImageView viewBackground = findViewById(R.id.viewBackground);
//        viewBackground.setImageBitmap(bitmap);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        viewBackground.setImageResource(R.drawable.chatbackground);
//        viewBackground.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        ImageView viewBackground = findViewById(R.id.viewBackground);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        viewBackground.setLayoutParams(layoutParams);
//        viewBackground.setImageResource(R.drawable.background);
//        viewBackground.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    private boolean sendMessage(Editable message) {
        User me = this.me;
        Message createdMessage = new Message(message.toString(), me, Utils.getTime());
        //messageDao.insert(createdMessage);
        addMsgToLocal(createdMessage);
        boolean success = true;
        //todo: talk with server shaked
        message.clear();
        return success;
    }

    private void addMsgToLocal(Message msg) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                messageDao.insertMessage(msg);
            }
        });
    }

    private void getMegssagesLocal() {
        // Inside your activity or fragment
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
            }
        });

    }

    @Override
    protected void onResume() {
        //todo: maybe update dataset from server
        super.onResume();
        //todo: maybe change?
        adapter.notifyDataSetChanged();
    }
}
