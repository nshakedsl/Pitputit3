package com.example.pitputitandroid.entities;

public class User {
    private String profilePic;
    private String username;
    private String displayName;

    public User(String profilePic, String username, String displayName) {
        this.profilePic = profilePic;
        this.username = username;
        this.displayName = displayName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
