package com.example.pitputitandroid.api;


import android.content.Context;
import android.util.Log;
import com.example.pitputitandroid.entities.Notification;
import com.example.pitputitandroid.entities.Firebase;
import com.example.pitputitandroid.entities.User;
import com.example.pitputitandroid.entities.UserFull;
import com.example.pitputitandroid.entities.UserLogin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import androidx.lifecycle.MutableLiveData;

import android.content.SharedPreferences;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserAPI {

    // Declare MutableLiveData to hold the login result
    private final MutableLiveData<Boolean> loginResult;
    private final MutableLiveData<Boolean> registerResult;
    private final MutableLiveData<Boolean> userDetailsResult;
    private static final String PREF_TOKEN = "token";
    private final SharedPreferences sharedPreferences;
    Retrofit retrofit;
    WebServiceAPI webServiceAPI;

    public UserAPI(Context context) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        loginResult = new MutableLiveData<>();
        registerResult = new MutableLiveData<>();
        userDetailsResult = new MutableLiveData<>();


    }

    public String getBaseUrl() {
        return sharedPreferences.getString("base_url", null);
    }

    public MutableLiveData<Boolean> getLoginResult() {
        return loginResult;
    }

    public MutableLiveData<Boolean> getRegisterResult() {
        return registerResult;
    }

    public MutableLiveData<Boolean> getUserDetailsResult() {
        return userDetailsResult;
    }

    public String getToken() {
        return sharedPreferences.getString(PREF_TOKEN, null);
    }



    public String getUsername() {
        return sharedPreferences.getString("username", null);
    }

    public void login(String username, String password) {
        UserLogin userLogin = new UserLogin(username, password);
        Call<String> call = webServiceAPI.login(userLogin);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String token = response.body();
                String finalToken = "Bearer " + token;

                if (token != null) {
                    // Save the token to SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(PREF_TOKEN, finalToken);
                    editor.apply();

                    // Set the login result as true
                    loginResult.postValue(true);
                } else {
                    // Set the login result as false
                    loginResult.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("TAG", "login failure");

                // Set the login result as false on failure
                loginResult.postValue(false);
            }
        });
    }

    public void register(String username, String password, String nickname, String profilePic) {
        UserFull userFull = new UserFull(password, profilePic, username, nickname);


        Call<Void> call = webServiceAPI.register(userFull);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 409)
                    registerResult.postValue(false);
                else if (response.code() == 200) {
                    registerResult.postValue(true);
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

                // Set the register result as false on failure
                registerResult.postValue(false);
            }
        });

    }


    public void saveToken(String username, String token) {
        Firebase firebase = new Firebase(username, token);
        Call<Void> call =webServiceAPI.saveToken(firebase);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });


    }

    public void sendNotification(String message, String username) {
        Notification notification = new Notification(message, username);
        Call<Void> call =webServiceAPI.sendNotification(notification);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });


    }

    public void getUserDetails(String userName) {

        Call<User> call = webServiceAPI.getUserDetails(getToken(), userName);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 401) {
                    userDetailsResult.postValue(false);
                } else if (response.code() == 200) {
                    User user = response.body();
                    if (user != null) {
                        // Save user details in SharedPreferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", user.getUsername());
                        editor.apply();
                    }

                    userDetailsResult.postValue(true);

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                userDetailsResult.postValue(false);
            }
        });


    }
}

