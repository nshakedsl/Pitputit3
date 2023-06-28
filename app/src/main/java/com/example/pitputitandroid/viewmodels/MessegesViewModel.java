package com.example.pitputitandroid.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.pitputitandroid.Daos.ChatDao;
import com.example.pitputitandroid.DataBase.AppDB;
import com.example.pitputitandroid.Repositories.ChatsRepository;
import com.example.pitputitandroid.Repositories.MarkedMessageRepository;
import com.example.pitputitandroid.Repositories.MessageRepository;
import com.example.pitputitandroid.entities.Chat;
import com.example.pitputitandroid.entities.MarkedMessage;
import com.example.pitputitandroid.entities.Message;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MessegesViewModel extends ViewModel {
    private MarkedMessageRepository messageRepository;
    private LiveData<List<Message>> messages;
    private ChatDao chatDao;
    private String origin;
    public MessegesViewModel(String origin) {
        this.origin = origin;
        this.messageRepository = new MarkedMessageRepository(origin);
        messages = messageRepository.getAll();
        chatDao = AppDB.getInstance().chatDao();
    }

    public LiveData<List<Message>> getMessages() {
        return messages;
    }
    public void add(Message message){
        messageRepository.add(message);
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            Chat chat = chatDao.getChat(origin);
            chat.setLastMessage(message);
            chatDao.updateChat(chat);
        });
    }
    //clears local dataset, then fills it with all of the given values
    public void set(List<Message> messages){ messageRepository.set(messages);}

    public void reload(){ messageRepository.reload();}
}
