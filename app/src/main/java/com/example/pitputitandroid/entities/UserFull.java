package com.example.pitputitandroid.entities;

public class UserFull {
    private String password;
    private String profilePic;
    private String username;
    private String displayName;

    public UserFull(String password, String profilePic, String username, String displayName) {
        this.password = password;
        this.profilePic = profilePic;
        this.username = username;
        this.displayName = displayName;
    }
}
