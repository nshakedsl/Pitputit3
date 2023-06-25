package com.example.pitputitandroid.api;

import com.example.pitputitandroid.entities.Chat;
import com.example.pitputitandroid.entities.Contact;
import com.example.pitputitandroid.entities.Message;
import com.example.pitputitandroid.entities.User;
import com.example.pitputitandroid.entities.UserFull;
import com.example.pitputitandroid.entities.UserLogin;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {
    @POST("Tokens")
    Call<String> login(@Body UserLogin userLogin);

    @GET("Chats")
    Call<List<Chat>> getChats(@Header("Authorization") String token);

    @POST("Chats")
    Call<Chat> createChat(@Header("Authorization") String token, @Body Contact contact);

    @GET("Chats/{id}/Messages")
    Call<List<Message>> getChatMessages(@Header("Authorization") String token, @Path("id") String id);

    @POST("Chats/{id}/Messages")
    Call<List<Message>> addChatMessage(@Header("Authorization") String token, @Body Message message, @Path("id") String id);

    @GET("Users/{username}")
    Call<User> getUserDetails(@Header("Authorization") String token, @Path("username") String username);

    @POST("Users")
    Call<Void> register(@Body UserFull userFull);
}
