package com.example.pitputitandroid.DataBase;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.pitputitandroid.Daos.ChatDao;
import com.example.pitputitandroid.entities.Chat;
/*
@Database(entities = {Chat.class}, version = 1)
public abstract class AppDB extends RoomDatabase{
    public abstract ChatDao chatDao();

}
*/