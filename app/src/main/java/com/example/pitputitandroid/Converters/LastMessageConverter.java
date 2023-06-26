package com.example.pitputitandroid.Converters;
import androidx.room.TypeConverter;

import com.example.pitputitandroid.entities.LastMessage;
import com.google.gson.Gson;

public class LastMessageConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static String fromLastMessage(LastMessage lastMessage) {
        return gson.toJson(lastMessage);
    }

    @TypeConverter
    public static LastMessage toLastMessage(String lastMessageString) {
        return gson.fromJson(lastMessageString, LastMessage.class);
    }
}
