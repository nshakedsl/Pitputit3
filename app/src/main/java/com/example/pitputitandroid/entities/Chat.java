package com.example.pitputitandroid.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Chat {

    @PrimaryKey(autoGenerate=true)
    private String id;
    private LastMessage lastMessage;
    private User user;

    public String getId() {
        return id;
    }

    public LastMessage getLastMessage() {
        return lastMessage;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastMessage(LastMessage lastMessage) {
        this.lastMessage = lastMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
