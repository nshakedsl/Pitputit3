package com.example.pitputitandroid.api;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.pitputitandroid.R;
import com.example.pitputitandroid.entities.Chat;
import com.example.pitputitandroid.entities.Contact;
import com.example.pitputitandroid.entities.Message;
import com.example.pitputitandroid.entities.Msg;
import com.example.pitputitandroid.entities.UserFull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

public class ChatAPI {
    WebServiceAPI webServiceAPI;
    Retrofit retrofit;
    private final MutableLiveData<Message> sendMessageResult;
    private final MutableLiveData<Map<Integer, Chat>> addChatResult;

    private final MutableLiveData<List<Chat>> chatsResult;
    private final MutableLiveData<List<Message>> chatMessagesResult;
    public ChatAPI(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        sendMessageResult = new MutableLiveData<>();

        addChatResult = new MutableLiveData<>();
        chatsResult = new MutableLiveData<>();
        chatMessagesResult= new MutableLiveData<>();



    }


    public MutableLiveData<Message> getSendMessageResult() {
        return sendMessageResult;
    }
    public MutableLiveData<Map<Integer, Chat>> getAddChatResult() {
        return addChatResult;
    }

    public MutableLiveData<List<Chat>> getChatsResult() {
        return chatsResult;
    }
    public MutableLiveData<List<Message>> getChatsMessageResult() {
        return chatMessagesResult;
    }
    public void sendMessage(String token, Msg message, String id) {

        Call<Message> call = webServiceAPI.addChatMessage(token,message,id);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.code() == 401)
                    sendMessageResult.postValue(null);
                else if (response.code() == 200) {
                    sendMessageResult.postValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

                // Set the register result as false on failure
                sendMessageResult.postValue(null);
            }
        });

    }



    public void addChat(String token, Contact contact) {

        Call<Chat> call = webServiceAPI.createChat(token,contact);
        call.enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                Map<Integer, Chat> tuple = new HashMap<>();
                tuple.put(response.code(), response.body());
                addChatResult.postValue(tuple);
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {

                // Set the register result as false on failure
                addChatResult.postValue(null);
            }
        });

    }



    public void getChats(String token) {

        Call<List<Chat>> call = webServiceAPI.getChats(token);
        call.enqueue(new Callback<List<Chat>>() {
            @Override
            public void onResponse(Call<List<Chat>> call, Response<List<Chat>> response) {

              if(response.code()==401)
                  chatsResult.postValue(null);
              else
                chatsResult.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Chat>> call, Throwable t) {

                // Set the register result as false on failure
                chatsResult.postValue(null);
            }
        });

    }

    public void getChatMessages(String token,String id) {

        Call<List<Message>> call = webServiceAPI.getChatMessages(token,id);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {

                if(response.code()==401)
                    chatMessagesResult.postValue(null);
                else
                    chatMessagesResult.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

                // Set the register result as false on failure
                chatsResult.postValue(null);
            }
        });

    }

}
