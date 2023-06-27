package com.example.pitputitandroid.entities;

import static com.example.pitputitandroid.Utils.decodeStringToBitmap;

import android.graphics.Bitmap;

import com.example.pitputitandroid.Utils;

public class Contact {
    private String id;
    private String username;
    private String lastMessageContent;
    private String picProfile;
    private String createdLastMessage;



    public Contact(String username, Bitmap picProfile, String lastMessageContent, String createdLastMessage) {
        this.createdLastMessage = createdLastMessage;
        this.username = username;
        this.lastMessageContent = lastMessageContent;
        this.id = Utils.uniqueIdGenerator();
        this.picProfile = Utils.encodeBitmapToString(picProfile);
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getLastMessageContent() {
        return lastMessageContent;
    }

    public String getPicProfile() {
        return picProfile;
    }

    public Bitmap getPicProfileBit() {
        return decodeStringToBitmap(this.picProfile);
    }

    public String getCreatedLastMessage() {
        return createdLastMessage;
    }
}
