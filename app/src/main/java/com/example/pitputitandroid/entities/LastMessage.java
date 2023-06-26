package com.example.pitputitandroid.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import com.example.pitputitandroid.Utils;

@Entity
public class LastMessage {
    public LastMessage(String created, String content) {
        this.created = created;
        this.content = content;
        this.id = Utils.uniqueIdGenerator();
    }
    public LastMessage(String created, String content, @NonNull String id) {
        this.created = created;
        this.content = content;
        this.id = id;
    }
    @PrimaryKey
    @NonNull
    protected String id;
    protected String created;
    protected String content;

    public String getCreated() {
        return created;
    }

    public String getContent() {
        return content;
    }
}
