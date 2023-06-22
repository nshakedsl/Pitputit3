package com.example.pitputitandroid.api;

import static com.example.pitputitandroid.PitputitAndroid.context;

import android.content.Context;
import android.util.Log;

import com.example.pitputitandroid.PitputitAndroid;
import com.example.pitputitandroid.R;
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
    private MutableLiveData<Boolean> loginResult;

    private static final String PREF_TOKEN = "token";
    private SharedPreferences sharedPreferences;
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

        retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        webServiceAPI = retrofit.create(WebServiceAPI.class);
        loginResult = new MutableLiveData<>();
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

    }

    public MutableLiveData<Boolean> getLoginResult() {
        return loginResult;
    }

    public String getToken() {
        return sharedPreferences.getString(PREF_TOKEN, null);
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
                Log.d("TAG", "Message to log");

                // Set the login result as false on failure
                loginResult.postValue(false);
            }
        });
    }
}

