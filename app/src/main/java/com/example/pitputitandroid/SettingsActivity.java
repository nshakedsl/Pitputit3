package com.example.pitputitandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import android.widget.CompoundButton.OnCheckedChangeListener;


import com.example.pitputitandroid.DataBase.AppDB;
import com.example.pitputitandroid.R;
import com.example.pitputitandroid.RegisterActivity;
import com.example.pitputitandroid.entities.Message;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SettingsActivity extends AppCompatActivity {

    Switch darkModeSwitch;


    private EditText editTextServerAddress;
    private AppCompatButton buttonSave;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        AppCompatImageView logoutButton = findViewById(R.id.logOut);
        logoutButton.setOnClickListener(v -> logOut());
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        AppCompatImageView backButton = findViewById(R.id.imageBackPage);
        backButton.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        //   update address

        editTextServerAddress = findViewById(R.id.serverIpInput);
        buttonSave = findViewById(R.id.updateButton);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String currentAddress = sharedPreferences.getString("BaseUrl", "");
        editTextServerAddress.setText(currentAddress);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newAddress = editTextServerAddress.getText().toString();

                // Save the new server address to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("BaseUrl", newAddress);
                editor.apply();

                // Finish the activity
                logOut();
            }
        });


        // update dark-mode

        darkModeSwitch = findViewById(R.id.dark_mode_switch);
        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    startActivity(new Intent(SettingsActivity.this, MainActivity.class));

                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                }


            }
        });

    }

    public void logOut() {
        //todo shaked: clear prefrences
        AppDB db = AppDB.getInstance(this);
        db.chatDao();
        //creates a thread that clears the messages
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.chatDao().clearChats();
                db.messageDao().clearMessages();
            }
        });

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
