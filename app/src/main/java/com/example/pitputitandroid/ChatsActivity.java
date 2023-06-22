package com.example.pitputitandroid;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pitputitandroid.adapters.MessegesListAdapter;
import com.example.pitputitandroid.entities.Message;

import java.io.ByteArrayOutputStream;
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



// Get the resource ID of the drawable
        int resourceId = R.drawable.user;

// Convert the drawable resource to a Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);




        List <Message> messages = new ArrayList<>();
        messages.add(new Message("hello everyone!!", "moshe", "mosh_nick", bitmap, "12:00" ));
        messages.add(new Message("hello this is ", "moshe", "mosh_nick", bitmap, "12:00" ));
        messages.add(new Message("hello world", "moshe", "mosh_nick", bitmap, "12:00" ));

        adapter.setMesseges(messages);




        ImageView viewBackground = findViewById(R.id.viewBackground);
//        ImageView viewBackground = findViewById(R.id.viewBackground);;
//        viewBackground.setImageBitmap(bitmap);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewBackground.setImageResource(R.drawable.chatbackground);
        viewBackground.setScaleType(ImageView.ScaleType.CENTER_CROP);


//        ImageView viewBackground = findViewById(R.id.viewBackground);
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        viewBackground.setLayoutParams(layoutParams);
//        viewBackground.setImageResource(R.drawable.background);
//        viewBackground.setScaleType(ImageView.ScaleType.CENTER_CROP);




    }
}
