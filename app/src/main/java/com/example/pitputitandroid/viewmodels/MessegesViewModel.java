package com.example.pitputitandroid.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.pitputitandroid.Repositories.ChatsRepository;
import com.example.pitputitandroid.Repositories.MarkedMessageRepository;
import com.example.pitputitandroid.Repositories.MessageRepository;
import com.example.pitputitandroid.entities.Chat;
import com.example.pitputitandroid.entities.MarkedMessage;
import com.example.pitputitandroid.entities.Message;

import java.util.List;


public class MessegesViewModel extends ViewModel {
    private MarkedMessageRepository messageRepository;
    private LiveData<List<Message>> messages;

    public MessegesViewModel(String origin) {
        this.messageRepository = new MarkedMessageRepository(origin);
        messages = messageRepository.getAll();
    }

    public LiveData<List<Message>> getMessages() {
        return messages;
    }
    public void add(Message message){ messageRepository.add(message);}
    //clears local dataset, then fills it with all of the given values
    public void set(List<Message> messages){ messageRepository.set(messages);}

    public void reload(){ messageRepository.reload();}
}
