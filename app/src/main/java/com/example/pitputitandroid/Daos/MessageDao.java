package com.example.pitputitandroid.Daos;

import androidx.lifecycle.LiveData;
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
    LiveData<List<Message>> indexMessage();

    @Query("SELECT * FROM message WHERE sender= :sender")
    LiveData<List<Message>> ofSender(String sender);

    @Query("SELECT * FROM message WHERE id = :id")
    Message getMessage(String id);

    @Query("DELETE FROM message")
    void clearMessages();
    @Insert
    void insertMessage(Message message);

    @Update
    void updateMessage(Message... messages);
    @Delete
    void deleteMessage(Message... messages);

}
