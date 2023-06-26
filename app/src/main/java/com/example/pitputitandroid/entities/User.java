package com.example.pitputitandroid.entities;

import android.graphics.Bitmap;

import com.example.pitputitandroid.Utils;

public class User {
    private String profilePic;
    private String username;
    private final String displayName;

    public User(String profilePic, String username, String displayName) {
        this.profilePic = profilePic;
        this.username = username;
        this.displayName = displayName;
    }
    public User(Bitmap profilePic, String username, String displayName) {
        this.profilePic = Utils.encodeBitmapToString(profilePic);
        this.username = username;
        this.displayName = displayName;
    }

    public String getProfilePic() {
        return profilePic;
    }
    public Bitmap getProfilePicBitmap(){ return Utils.decodeStringToBitmap(profilePic); }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
    public void setProfilePic(Bitmap profilePic) {
        this.profilePic = Utils.encodeBitmapToString(profilePic);
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
