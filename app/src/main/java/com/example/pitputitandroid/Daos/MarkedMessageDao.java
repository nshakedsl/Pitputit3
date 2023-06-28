package com.example.pitputitandroid.Daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pitputitandroid.entities.Chat;
import com.example.pitputitandroid.entities.MarkedMessage;
import com.example.pitputitandroid.entities.Message;

import java.util.List;

@Dao
public interface MarkedMessageDao {

    @Query("SELECT * FROM markedMessage")
    List<MarkedMessage> indexMarkedMessage();

    @Query("SELECT * FROM markedMessage WHERE originId= :originId")
    List<MarkedMessage> ofSender(String originId);

    @Query("SELECT * FROM markedMessage WHERE id = :id")
    MarkedMessage getMessage(String id);

    @Query("DELETE FROM markedMessage")
    void clearMessages();

    @Query("DELETE FROM markedMessage WHERE originId= :originId")
    void clearMessagesOfOrigin(String originId);
    @Insert
    void insertMessage(MarkedMessage markedMessage);

    @Update
    void updateMessage(MarkedMessage... markedMessages);
    @Delete
    void deleteMessage(MarkedMessage... markedMessages);

}
