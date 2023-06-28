package com.example.pitputitandroid.Repositories;

import static com.example.pitputitandroid.PitputitAndroid.context;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pitputitandroid.Daos.ChatDao;
import com.example.pitputitandroid.DataBase.AppDB;
import com.example.pitputitandroid.api.ChatAPI;
import com.example.pitputitandroid.entities.Chat;
import com.example.pitputitandroid.entities.LastMessage;
import com.example.pitputitandroid.entities.User;

import java.util.ArrayList;
import java.util.List;

public class ChatsRepository {
    private ChatDao dao;
    private ChatListData chatListData;
    private ChatAPI chatAPI;

    public ChatsRepository() {
        AppDB db = AppDB.getInstance();
        dao = db.chatDao();
        chatListData = new ChatListData();
    }

    class ChatListData extends MutableLiveData<List<Chat>> {
        public ChatListData() {
            super();
            new Thread(() ->
            {
                chatListData.postValue(new ArrayList<>());
            }).start();
        }

        @Override
        public void onActive() {
            super.onActive();
            new Thread(() ->
            {
                if (dao.indexChat() != null)
                    chatListData.postValue(dao.indexChat());

            }).start();
        }

    }

    public LiveData<List<Chat>> getAll() {
        return chatListData;
    }

    public void add(final Chat chat) {
        new Thread(() ->
        {
            dao.insertChat(chat);
            chatListData.postValue(dao.indexChat());
        }).start();
        //todo: shaked
        //chatAPI.addChat(chat);
    }

    public void reload() {
        //todo: shaked
        //chatAPI.getChats();
    }
}
