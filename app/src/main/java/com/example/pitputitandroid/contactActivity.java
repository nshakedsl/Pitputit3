package com.example.pitputitandroid;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pitputitandroid.Daos.ChatDao;
import com.example.pitputitandroid.DataBase.AppDB;
import com.example.pitputitandroid.adapters.ContactAdapter;
import com.example.pitputitandroid.adapters.ContactAdapterRV;
import com.example.pitputitandroid.adapters.ContactListAdapter;
import com.example.pitputitandroid.adapters.MessegesListAdapter;
import com.example.pitputitandroid.databinding.ActivityMainBinding;
import com.example.pitputitandroid.entities.Chat;
import com.example.pitputitandroid.entities.Contact;
import com.example.pitputitandroid.entities.LastMessage;
import com.example.pitputitandroid.entities.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ContactActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private AppDB db;
    private ChatDao chatDao;

    private User me;
    private ContactListAdapter adapter;
    private List<Chat> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


        EditText editText = findViewById(R.id.inputNickname);
        db = AppDB.getInstance(this);
        chatDao = db.chatDao();
        FloatingActionButton addBtn = findViewById(R.id.btnAdd);
        addBtn.setOnClickListener(v -> addMsg(editText.getText()));



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

        Chat c3 = new Chat();
        c3.setUser(new User(bitmap, "moshe", "moshi"));
        c3.setLastMessage(new LastMessage("12:00", "hello"));
        Chat c4 = new Chat();
        c4.setUser(new User(bitmap, "moshe1", "moshi1"));
        c4.setLastMessage(new LastMessage("12:01", "hello1"));
        Chat c5 = new Chat();
        c5.setUser(new User(bitmap, "moshe2", "moshi2"));
        c5.setLastMessage(new LastMessage("12:02", "hello2"));

        contacts.add(c);
        contacts.add(c1);
        contacts.add(c2);
        contacts.add(c3);
        contacts.add(c4);
        contacts.add(c5);
        adapter.setContacts(contacts);
        //code click...

//        this.adapter = new ContactListAdapter(this);
//
//        lstMesseges.setAdapter(adapter);
//        lstMesseges.setLayoutManager(new LinearLayoutManager(this));
//
//
//
//
//        //code of listview
//        binding= ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        int[]imageId ={R.drawable.user, R.drawable.user, R.drawable.user};
//        String[] name={"ff","aa", "bb"};
//        String[] lastMessageContent = {"ff","aa", "bb"};
//        String[] date={"ff","aa", "bb"};
//        ArrayList <Contact> arrayList= new ArrayList<>();
//
//        ListView listView = findViewById(R.id.lvContacts);
//        ContactAdapter contactAdapter = new ContactAdapter(this, arrayList);
//        lvContacts.setAdapter(contactAdapter);


    }
    private void addMsg(Editable nicknameFiled){
        //todo: find the relevant chat
        //somehow define chat
        Chat chat = new Chat();
        addMsgToLocal(chat);
        // TODO: Shaked 26/06/2023 add the created chat to server
    }
    private void addMsgToLocal(Chat chat) {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                chatDao.insertChat(chat);
            }
        });
    }
}




//package com.example.pitputitandroid;
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.text.Editable;
//import android.util.Log;
//import android.widget.EditText;
//import android.widget.ListView;
//import androidx.appcompat.app.AppCompatActivity;
//import com.example.pitputitandroid.Daos.ChatDao;
//import com.example.pitputitandroid.DataBase.AppDB;
//import com.example.pitputitandroid.adapters.ContactAdapter;
//import com.example.pitputitandroid.databinding.ActivityMainBinding;
//import com.example.pitputitandroid.entities.Chat;
//import com.example.pitputitandroid.entities.Contact;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//
//public class ContactActivity extends AppCompatActivity {
//    ActivityMainBinding binding;
//    private AppDB db;
//    private ChatDao chatDao;
//
//    private ListView lvContacts;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_contact);
//        EditText editText = findViewById(R.id.inputNickname);
//        db = AppDB.getInstance(this);
//        chatDao = db.chatDao();
//        FloatingActionButton addBtn = findViewById(R.id.btnAdd);
//        addBtn.setOnClickListener(v -> addMsg(editText.getText()));
//
//        lvContacts = findViewById(R.id.lvContacts);
//
//        //code of listview
//        binding= ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        int[]imageId ={R.drawable.user, R.drawable.user, R.drawable.user};
//        String[] name={"ff","aa", "bb"};
//        String[] lastMessageContent = {"ff","aa", "bb"};
//        String[] date={"ff","aa", "bb"};
//        ArrayList <Contact> arrayList= new ArrayList<>();
//
//        ListView listView = findViewById(R.id.lvContacts);
//        ContactAdapter contactAdapter = new ContactAdapter(this, arrayList);
//        lvContacts.setAdapter(contactAdapter);
//
//
//    }
//    private void addMsg(Editable nicknameFiled){
//        //todo: find the relevant chat
//        //somehow define chat
//        Chat chat = new Chat();
//        addMsgToLocal(chat);
//        // TODO: Shaked 26/06/2023 add the created chat to server
//    }
//    private void addMsgToLocal(Chat chat) {
//        Executor executor = Executors.newSingleThreadExecutor();
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                chatDao.insertChat(chat);
//            }
//        });
//    }
//}
//
