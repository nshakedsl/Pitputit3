package com.example.pitputitandroid;

import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pitputitandroid.adapters.MessegesListAdapter;
import com.example.pitputitandroid.entities.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        RecyclerView lstMesseges = findViewById(R.id.lstMesseges);

        final MessegesListAdapter adapter = new MessegesListAdapter(this);
        lstMesseges.setAdapter(adapter);
        lstMesseges.setLayoutManager( new LinearLayoutManager(this));

        List <Message> messeges = new ArrayList<>();
        messeges.add(new Message("hello", "moshe", "mosh_nick", R.drawable.user, "12:00" ));

    }
}
