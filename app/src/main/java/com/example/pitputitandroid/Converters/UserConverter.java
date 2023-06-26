package com.example.pitputitandroid.Converters;
import androidx.room.TypeConverter;

import com.example.pitputitandroid.entities.User;
import com.google.gson.Gson;

public class UserConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static String fromLastMessage(User user) {
        return gson.toJson(user);
    }

    @TypeConverter
    public static User toLastMessage(String user) {
        return gson.fromJson(user, User.class);
    }
}
