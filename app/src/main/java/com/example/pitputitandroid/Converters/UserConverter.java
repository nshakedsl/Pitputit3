package com.example.pitputitandroid.Converters;
import android.util.Log;

import androidx.room.TypeConverter;

import com.example.pitputitandroid.entities.User;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class UserConverter {
    private static final Gson gson = new Gson();

    @TypeConverter
    public static String fromLastMessage(User user) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("profilePic", user.getProfilePic());
            jsonObject.put("username", user.getUsername());
            jsonObject.put("displayName", user.getDisplayName());
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("json error",e.toString());
        }
        return null;
    }

    @TypeConverter
    public static User toLastMessage(String userStr) {
        try {
            JSONObject jsonObject = new JSONObject(userStr);
            String profilePic = jsonObject.optString("profilePic");
            String username = jsonObject.optString("username");
            String displayName = jsonObject.optString("displayName");
            return new User(profilePic,username,displayName);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("json error",e.toString());
            return null;
        }
    }
}
