package com.example.pitputitandroid.entities;

import androidx.room.PrimaryKey;

import androidx.room.Entity;


@Entity
public class Message extends LastMessage{

@PrimaryKey(autoGenerate = true)
private int id;

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }
        private User sender;

         public Message(String content, String userName, String displayName, int imgProfile, String time ) {
         this.content = content;
         }

         public String getTime() {
         return created;
         }

         public String getImgProfile() {
         return sender.getProfilePic();
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

         public void setUserName(String userName) {
  sender.setUsername(userName);
 }

         public void setDisplayName(String displayName) {
             sender.setDisplayName(displayName);
 }

        public void setImgProfile(String profilePic) {
            sender.setProfilePic(profilePic);
    }


         public void setTime(String time) {
         this.created = time;
         }

         public void setContent(String content) {
         this.content = content;
         }
}
