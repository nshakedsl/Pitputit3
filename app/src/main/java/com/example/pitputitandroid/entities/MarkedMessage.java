package com.example.pitputitandroid.entities;

import androidx.annotation.NonNull;
import androidx.room.PrimaryKey;

import androidx.room.Entity;
import androidx.room.TypeConverters;

import com.example.pitputitandroid.Converters.UserConverter;
import com.example.pitputitandroid.Utils;

@TypeConverters(UserConverter.class)
@Entity
public class MarkedMessage{
    @PrimaryKey
    @NonNull
    private String id;
    private final User sender;
    private final String created;
    private final String content;
    private final String originId;

    public MarkedMessage(Message message, String originId) {
        this.created = message.getCreated();
        this.content = message.getContent();
        this.sender = message.getSender();
        this.id = Utils.uniqueIdGenerator();
        this.originId = originId;
    }
    public MarkedMessage(User sender,String created,String content, String originId) {
        this.created = created;
        this.content = content;
        this.sender = sender;
        this.originId = originId;
        this.id = Utils.uniqueIdGenerator();
    }

    public String getOriginId() {
        return originId;
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

    public boolean compare(MarkedMessage message){
        if(!message.getContent().equals(this.getContent()))
            return false;
        if(!message.getCreated().equals(this.getCreated()))
            return false;
        return message.sender.getUsername().equals(this.sender.getUsername());
    }

    public Message createMessage(){
        return new Message(content,sender,created);
    }

}
