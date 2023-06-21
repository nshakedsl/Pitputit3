package com.example.pitputitandroid;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

public class ChatsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        RecyclerView lstMesseges =findViewById(R.id.lstMesseges);

    }
}
