package com.example.pitputitandroid.entities;

import androidx.room.PrimaryKey;

import androidx.room.Entity;


@Entity
public class Messege {

@PrimaryKey(autoGenerate = true)
private int id;

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

        private String time;
        private String content;
        private String userName;
        private String displayName;

         public Messege(String content) {
         this.content = content;
         }

         public String getTime() {
         return time;
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


         public void setTime(String time) {
         this.time = time;
         }

         public void setContent(String content) {
         this.content = content;
         }
}
