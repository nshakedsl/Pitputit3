package com.example.pitputitandroid.DataBase;

import static com.example.pitputitandroid.PitputitAndroid.context;

import android.content.Context;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.pitputitandroid.Converters.LastMessageConverter;
import com.example.pitputitandroid.Converters.UserConverter;
import com.example.pitputitandroid.Daos.ChatDao;
import com.example.pitputitandroid.Daos.MarkedMessageDao;
import com.example.pitputitandroid.Daos.MessageDao;
import com.example.pitputitandroid.entities.Chat;
import com.example.pitputitandroid.entities.Message;

@TypeConverters({LastMessageConverter.class, UserConverter.class})
@Database(entities = {Chat.class, Message.class}, version = 6)
public abstract class AppDB extends RoomDatabase{
    private static AppDB instance;

    public abstract ChatDao chatDao();
    public abstract MessageDao messageDao();
    public abstract MarkedMessageDao markedMessageDao();

    public static synchronized AppDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDB.class, "app_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    public static synchronized AppDB getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDB.class, "app_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
