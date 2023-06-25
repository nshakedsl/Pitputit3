package com.example.pitputitandroid.entities;

import android.graphics.Bitmap;

import androidx.room.PrimaryKey;

import androidx.room.Entity;


@Entity
public class Message extends LastMessage {

    private final User sender;


    public Message(String content, User sender, String created) {
        super(created, content);
        this.sender = sender;
    }

    public String getTime() {
        return created;
    }

    public String getContent() {
        return content;
    }

    public String getUserName() {
        return sender.getUsername();
    }

    public String getDisplayName() {
        return sender.getDisplayName();
    }

}
