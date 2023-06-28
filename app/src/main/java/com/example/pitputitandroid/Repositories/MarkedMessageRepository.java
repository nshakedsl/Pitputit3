package com.example.pitputitandroid.Repositories;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pitputitandroid.Daos.MarkedMessageDao;
import com.example.pitputitandroid.DataBase.AppDB;
import com.example.pitputitandroid.entities.MarkedMessage;
import com.example.pitputitandroid.entities.Message;

import java.util.ArrayList;
import java.util.List;

public class MarkedMessageRepository {
    private MarkedMessageDao dao;
    private MarkedMessageListData messageListData;

    public MarkedMessageRepository() {
        AppDB db = AppDB.getInstance();
        dao = db.markedMessageDao();
        messageListData = new MarkedMessageListData();
    }

    class MarkedMessageListData extends MutableLiveData<List<MarkedMessage>> {
        public MarkedMessageListData() {
            super();
            new Thread(() ->
            {
                messageListData.postValue(new ArrayList<>());
            }).start();
        }

        @Override
        public void onActive() {
            super.onActive();
            new Thread(() ->
            {
                if (dao.indexMarkedMessage() != null)
                    messageListData.postValue(dao.indexMarkedMessage());

            }).start();
        }

    }

    public LiveData<List<MarkedMessage>> getAll() {
        return messageListData;
    }

    public void add(final MarkedMessage message) {
        new Thread(() ->
        {
            dao.insertMessage(message);
            messageListData.postValue(dao.indexMarkedMessage());
        }).start();
        //todo: shaked
        //chatAPI.addChat(chat);
    }

    public void reload() {
        new Thread(() ->
        {
            messageListData.postValue(dao.indexMarkedMessage());
        }).start();
        //todo: shaked
        //chatAPI.getChats();
    }
    //clears old dataset, and fills it with the new values, then sets the vals to the new values
    public void set(List<MarkedMessage> messages){
        new Thread(() ->
        {
            dao.clearMessages();
            for (MarkedMessage message: messages) {
                dao.insertMessage(message);
            }
            messageListData.postValue(dao.indexMarkedMessage());
        }).start();
    }
}
