package com.example.pitputitandroid.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.pitputitandroid.Repositories.ChatsRepository;
import com.example.pitputitandroid.entities.Chat;

import java.util.List;

public class ChatViewModel extends ViewModel {
    private ChatsRepository chatsRepository;
    private LiveData<List<Chat>> chats;

    public ChatViewModel() {
        this.chatsRepository = new ChatsRepository();
        chats = chatsRepository.getAll();
    }

    public LiveData<List<Chat>> getChats() {
        return chats;
    }
    public void add(Chat chat){ chatsRepository.add(chat);}
    public void reload(){ chatsRepository.reload();}
}
