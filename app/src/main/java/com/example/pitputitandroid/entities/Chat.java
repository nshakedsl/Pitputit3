package com.example.pitputitandroid.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.pitputitandroid.Utils;

@Entity
public class Chat {

    public Chat() {
        this.id  = Utils.uniqueIdGenerator();
    }
    public void setLastMessage(LastMessage lastMessage) {
        this.lastMessage = lastMessage;
    }
    @PrimaryKey
    @NonNull
    private String id;
    private LastMessage lastMessage;
    private User user;

    @NonNull
    public String getId() {
        return id;
    }

    public LastMessage getLastMessage() {
        return lastMessage;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
