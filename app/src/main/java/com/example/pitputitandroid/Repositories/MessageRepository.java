package com.example.pitputitandroid.Repositories;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.pitputitandroid.Daos.MessageDao;
import com.example.pitputitandroid.DataBase.AppDB;
import com.example.pitputitandroid.api.ChatAPI;
import com.example.pitputitandroid.entities.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageRepository {
    private MessageDao dao;
    private MessageListData messageListData;
    private ChatAPI chatAPI;

    public MessageRepository() {
        AppDB db = AppDB.getInstance();
        dao = db.messageDao();
        messageListData = new MessageListData();
    }

    class MessageListData extends MutableLiveData<List<Message>> {
        public MessageListData() {
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
                if (dao.indexMessage() != null)
                    messageListData.postValue(dao.indexMessage());

            }).start();
        }

    }

    public LiveData<List<Message>> getAll() {
        return messageListData;
    }

    public void add(final Message message) {
        new Thread(() ->
        {
            dao.insertMessage(message);
            messageListData.postValue(dao.indexMessage());
        }).start();
        //todo: shaked
        //chatAPI.addChat(chat);
    }

    public void reload() {
        new Thread(() ->
        {
            messageListData.postValue(dao.indexMessage());
        }).start();
        //todo: shaked
        //chatAPI.getChats();
    }
    //clears old dataset, and fills it with the new values, then sets the vals to the new values
    public void set(List<Message> messages){
        new Thread(() ->
        {
            dao.clearMessages();
            for (Message message: messages) {
                dao.insertMessage(message);
            }
            messageListData.postValue(dao.indexMessage());
        }).start();
    }
}
