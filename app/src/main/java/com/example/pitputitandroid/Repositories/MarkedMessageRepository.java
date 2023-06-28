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
    private String senderId;

    public MarkedMessageRepository(String senderId) {
        AppDB db = AppDB.getInstance();
        dao = db.markedMessageDao();
        messageListData = new MarkedMessageListData();
        this.senderId = senderId;
    }

    class MarkedMessageListData extends MutableLiveData<List<Message>> {
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
                if (dao.ofSender(senderId) != null)
                    messageListData.postValue(genMsgs(dao.ofSender(senderId)));
            }).start();
        }

    }

    public LiveData<List<Message>> getAll() {
        return messageListData;
    }

    public void add(final MarkedMessage message) {
        new Thread(() ->
        {
            dao.insertMessage(message);
            messageListData.postValue(genMsgs(dao.ofSender(senderId)));
        }).start();
        //todo: shaked
        //chatAPI.addChat(chat);
    }

    public void add(final Message message) {
        new Thread(() ->
        {
            dao.insertMessage(new MarkedMessage(message, senderId));
            messageListData.postValue(genMsgs(dao.ofSender(senderId)));
        }).start();
        //todo: shaked
        //chatAPI.addChat(chat);
    }

    public void reload() {
        new Thread(() ->
        {
            messageListData.postValue(genMsgs(dao.ofSender(senderId)));
        }).start();
        //todo: shaked
        //chatAPI.getChats();
    }

    //clears old dataset, and fills it with the new values, then sets the vals to the new values
    public void setFromMarked(List<MarkedMessage> messages) {
        new Thread(() ->
        {
            dao.clearMessages();
            for (MarkedMessage message : messages) {
                dao.insertMessage(message);
            }
            messageListData.postValue(genMsgs(dao.ofSender(senderId)));
        }).start();
    }

    public void set(List<Message> messages) {
        new Thread(() ->
        {
            dao.clearMessagesOfOrigin(senderId);
            for (Message message : messages) {
                dao.insertMessage(new MarkedMessage(message, senderId));
            }
            messageListData.postValue(genMsgs(dao.ofSender(senderId)));
        }).start();
    }

    private List<Message> genMsgs(List<MarkedMessage> markedMessages) {
        List<Message> msgs = new ArrayList<>();
        for (MarkedMessage msg : markedMessages) {
            msgs.add(msg.createMessage());
        }
        return msgs;
    }
}
