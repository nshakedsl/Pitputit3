package com.example.pitputitandroid.DataBase;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.pitputitandroid.Converters.LastMessageConverter;
import com.example.pitputitandroid.Converters.UserConverter;
import com.example.pitputitandroid.Daos.ChatDao;
import com.example.pitputitandroid.entities.Chat;

@TypeConverters({LastMessageConverter.class, UserConverter.class})
@Database(entities = {Chat.class}, version = 1)
public abstract class AppDB extends RoomDatabase{
    public abstract ChatDao chatDao();
}
