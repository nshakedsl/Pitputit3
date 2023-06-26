package com.example.pitputitandroid;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.pitputitandroid.entities.LastMessage;

import java.util.concurrent.ExecutionException;
@TypeConverters({Converter.class})
public class Converter {
    @TypeConverter
    String fromLastMessage(LastMessage lastMessage){
        return lastMessage.getCreated() +"$" +lastMessage.getContent();
    }
    @TypeConverter
    LastMessage toLastMessage(String lastMessage){
        String[] parts = lastMessage.split("\\$");

        if (parts.length >= 2) {
            String firstPart = parts[0];
            String secondPart = lastMessage.substring(firstPart.length() + "\\$".length());
            return new LastMessage(firstPart,secondPart);
        } else {
            throw new RuntimeException("illegal value given");
        }
    }
}
