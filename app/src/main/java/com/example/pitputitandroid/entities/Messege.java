package com.example.pitputitandroid.entities;

import android.graphics.Bitmap;

import androidx.room.PrimaryKey;

import androidx.room.Entity;


@Entity
public class Messege {

@PrimaryKey(autoGenerate = true)
private int id;

    private String time;
    private String content;
    private String userName;
    private String displayName;
    private Bitmap imgProfile;
 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }



         public Messege(String content,String userName,String displayName,Bitmap imgProfile, String time ) {
         this.content = content;
         this.userName=userName;
         this.displayName=displayName;
         this.time=time;
         this.imgProfile=imgProfile;
         }

         public String getTime() {
         return time;
         }

         public Bitmap getImgProfile() {
         return imgProfile;
         }

        public String getContent() {
        return content;
    }

         public String getUserName() {
  return userName;
 }

         public String getDisplayName() {
  return displayName;
 }

         public void setUserName(String userName) {
  this.userName = userName;
 }

         public void setDisplayName(String displayName) {
  this.displayName = displayName;
 }

        public void setImgProfile(Bitmap imgProfile) {
        this.imgProfile = imgProfile;
    }


         public void setTime(String time) {
         this.time = time;
         }

         public void setContent(String content) {
         this.content = content;
         }
}
