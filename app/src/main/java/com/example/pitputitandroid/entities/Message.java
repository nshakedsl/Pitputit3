package com.example.pitputitandroid.entities;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import androidx.room.Entity;

import com.example.pitputitandroid.Utils;


@Entity
public class Message {
    @PrimaryKey
    @NonNull
    private String id;
    private final User sender;
    private final String created;
    private final String content;

    public Message(String content, User sender, String created) {
        this.created = created;
        this.content = content;
        this.sender = sender;
        this.id = Utils.uniqueIdGenerator();
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getCreated() {
        return created;
    }

    public User getSender() {
        return sender;
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
