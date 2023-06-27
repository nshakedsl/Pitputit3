package com.example.pitputitandroid.entities;

import android.graphics.Bitmap;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import androidx.room.Entity;
import androidx.room.TypeConverters;

import java.io.ByteArrayOutputStream;

import com.example.pitputitandroid.Converters.UserConverter;
import com.example.pitputitandroid.Utils;

@TypeConverters(UserConverter.class)
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

    public Message(String content,String userName, String displayName, Bitmap bitmap, String created) {
        this.created = created;
        this.content = content;
        this.sender = new User(bitmap, userName, displayName);
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
