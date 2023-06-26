package com.example.pitputitandroid.Daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pitputitandroid.entities.Chat;
import com.example.pitputitandroid.entities.Message;

import java.util.List;

@Dao
public interface MessageDao {

    @Query("SELECT * FROM message")
    List<Message> indexMessage();

    //@Query("SELECT * FROM message WHERE sender= :sender")
    //List<Chat> ofSender(String sender);

    @Query("SELECT * FROM message WHERE id = :id")
    Message getMessage(String id);

    @Insert
    void insertMessage(Message... messages);

    @Update
    void updateMessage(Message... messages);
    @Delete
    void deleteMessage(Message... messages);

}
