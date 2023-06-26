package com.example.pitputitandroid.entities;

import android.graphics.Bitmap;

public class User {
    private Bitmap profilePic;
    private String username;
    private final String displayName;

    public User(Bitmap profilePic, String username, String displayName) {
        this.profilePic = profilePic;
        this.username = username;
        this.displayName = displayName;
    }

    public Bitmap getProfilePic() {
        return profilePic;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = profilePic;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
