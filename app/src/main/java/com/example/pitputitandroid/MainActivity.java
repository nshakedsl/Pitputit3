package com.example.pitputitandroid;

import static com.example.pitputitandroid.PitputitAndroid.context;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;

import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pitputitandroid.api.UserAPI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;
import androidx.navigation.ui.AppBarConfiguration;


public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatButton loginButton = findViewById(R.id.loginButton);
        AppCompatImageView settingButton = findViewById(R.id.imageSettings);
        //todo: check with database Before sending
        EditText editText = findViewById(R.id.usernameInput);
        EditText passText = findViewById(R.id.passwordInput);
        ImageView imglogo = findViewById(R.id.imageView2);



        loginButton.setOnClickListener(v ->
                loginClick(editText.getText(), passText.getText()));
        TextView registerText = findViewById(R.id.registerText);

        //sends to register/signup page
        registerText.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

        settingButton.setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });


        TextView welcomeText = findViewById(R.id.textView);
        welcomeText.animate().translationY(50).setDuration(700).setStartDelay(0);
        passText.animate().translationY(50).setDuration(700).setStartDelay(0);
        editText.animate().translationY(50).setDuration(700).setStartDelay(0);
        registerText.animate().translationY(50).setDuration(700).setStartDelay(0);
        imglogo.animate().translationY(50).setDuration(700).setStartDelay(0);
        loginButton.animate().translationY(50).setDuration(700).setStartDelay(0);

    }

    void loginClick(Editable username, Editable password) {
        Intent I = new Intent(this, ContactActivity.class);


        UserAPI userAPI = new UserAPI(getApplicationContext());
        userAPI.login(username.toString(), password.toString());
        // Observe the login result using the MutableLiveData returned by getLoginResult()
        userAPI.getLoginResult().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSuccess) {
                if (isSuccess) {
                    // Login successful and proceed to ChatsActivity
                    userAPI.getUserDetails(username.toString());
                } else {
                    String result = "incorrect password or/and username❗";
                    // Login failed, handle the error
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                }
            }
        });

        userAPI.getUserDetailsResult().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSuccess2) {
                if (isSuccess2) {
                    // Get user details successful
                    startActivity(I);

                } else {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

}