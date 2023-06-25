package com.example.pitputitandroid.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LastMessage {
    public LastMessage(String created, String content) {
        this.created = created;
        this.content = content;
    }

    @PrimaryKey(autoGenerate=true)
    protected String id;
    protected String created;
    protected String content;

}
