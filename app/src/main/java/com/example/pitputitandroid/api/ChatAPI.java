package com.example.pitputitandroid.api;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.pitputitandroid.R;
import com.example.pitputitandroid.entities.Message;
import com.example.pitputitandroid.entities.Msg;
import com.example.pitputitandroid.entities.UserFull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


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


    }

    private final MutableLiveData<Message> sendMessageResult;

    public MutableLiveData<Message> getSendMessageResult() {
        return sendMessageResult;
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
}
