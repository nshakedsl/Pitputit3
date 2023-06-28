package com.example.pitputitandroid.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.pitputitandroid.Utils;

@Entity
public class Chat {

    public Chat(@NonNull LastMessage lastMessage, @NonNull User user) {
        this.id  = Utils.uniqueIdGenerator();
        this.lastMessage = lastMessage;
        this.user = user;
    }
    public void setLastMessage(@NonNull LastMessage lastMessage) {
        this.lastMessage = lastMessage;
    }
    public void setLastMessage(@NonNull Message message) {
        this.lastMessage = new LastMessage(message);
    }
    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private LastMessage lastMessage;
    @NonNull
    private User user;

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    public LastMessage getLastMessage() {
        return lastMessage;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public User getUser() {
        return user;
    }

    public void setUser(@NonNull User user) {
        this.user = user;
    }
}
