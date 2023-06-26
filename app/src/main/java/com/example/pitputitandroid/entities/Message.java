package com.example.pitputitandroid.entities;

import android.graphics.Bitmap;
import android.util.Base64;

import androidx.room.PrimaryKey;

import androidx.room.Entity;

import java.io.ByteArrayOutputStream;


@Entity
public class Message extends LastMessage{

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
        private User sender;



         public Message(String content,String userName,String displayName,Bitmap imgProfile, String time ) {
         this.content = content;
         this.userName=userName;
         this.displayName=displayName;
         this.time=time;
         this.imgProfile=imgProfile;
         }


         public String CastToString(Bitmap bitmapPic){
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             bitmapPic.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
             byte[] byteArray = byteArrayOutputStream.toByteArray();
             String encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
             return encodedImage;
         }

         public String getTime() {
         return created;
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
  return sender.getDisplayName();
 }

         public void setUserName(String userName) {
  sender.setUsername(userName);
 }

         public void setDisplayName(String displayName) {
             sender.setDisplayName(displayName);
 }

        public void setImgProfile(Bitmap imgProfile) {
        this.imgProfile = imgProfile;
    }


         public void setTime(String time) {
         this.created = time;
         }

         public void setContent(String content) {
         this.content = content;
         }
}
