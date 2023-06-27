package com.example.pitputitandroid;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.L;
import com.example.pitputitandroid.Daos.MessageDao;
import com.example.pitputitandroid.DataBase.AppDB;
import com.example.pitputitandroid.adapters.ContactListAdapter;
import com.example.pitputitandroid.databinding.ActivityMainBinding;
import com.example.pitputitandroid.entities.Contact;

import java.util.ArrayList;
import java.util.List;

public class contactActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[]imageId ={R.drawable.user, R.drawable.user, R.drawable.user};
        String[] name={"ff","aa", "bb"};
        String[] lastMessageContent = {"ff","aa", "bb"};
        String[] date={"ff","aa", "bb"};
        ArrayList <Contact> arrayList= new ArrayList<>();
        for(int i = 0; i < imageId.length; i++){
            Contact contact = new Contact(name[i], Utils.decodeStringToBitmap(lastMessageContent[i]),lastMessageContent[i], date[i]);
            arrayList.add(contact);


        }
//        ListAdapter listAdapter = new ListAdapter(contactActivity.this, arrayList);
//        binding.


//    List<Contact> list;
//
//    private AppDB db;
//    private MessageDao messageDao;
//    Activity
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_contact);
//
//        ListView lvItems =findViewById(R.id.lvContacts);
//
//        int resourceId = R.drawable.user;
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId);
//
//        list = new ArrayList<>();
//        list.add(new Contact("aa", bitmap, "hi1", "15:00"));
//        list.add(new Contact("bb", bitmap, "hi2", "15:01"));


//        ContactListAdapter adapter = new ContactListAdapter(getApplicationContext(), );
//
//        lvItems.setAdapter(adapter);


    }

}
