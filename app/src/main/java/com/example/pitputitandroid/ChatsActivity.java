package com.example.pitputitandroid;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pitputitandroid.adapters.MessegesListAdapter;
import com.example.pitputitandroid.entities.Messege;

import java.util.ArrayList;
import java.util.List;

public class ChatsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        RecyclerView lstMesseges = findViewById(R.id.lstMesseges);

        final MessegesListAdapter adapter = new MessegesListAdapter(this);
        lstMesseges.setAdapter(adapter);
        lstMesseges.setLayoutManager( new LinearLayoutManager(this));

        List < Messege> messages = new ArrayList<>();
        messages.add(new Messege("hello", "moshe", "mosh_nick", R.drawable.user, "12:00" ));
        messages.add(new Messege("hello", "moshe", "mosh_nick", R.drawable.user, "12:00" ));
        messages.add(new Messege("hello", "moshe", "mosh_nick", R.drawable.user, "12:00" ));

        adapter.setMesseges(messages);


    }
}
