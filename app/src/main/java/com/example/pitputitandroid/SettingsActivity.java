package com.example.pitputitandroid;

import static com.example.pitputitandroid.PitputitAndroid.context;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;




import com.example.pitputitandroid.DataBase.AppDB;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SettingsActivity extends AppCompatActivity {

    Switch darkModeSwitch;
    private ConstraintLayout layout;
    private static final String KEY_BASE_URL = "base_url";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_DISPLAY_NAME = "display_name";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PROFILE_PIC = "profile_pic";


    private static final String PREFS_NAME = "prefs";
    private static final String KEY_DARK_MODE = "darkMode";

    private EditText editTextServerAddress;
    private AppCompatButton buttonSave;
    private SharedPreferences sharedPreferences;

    private AppCompatButton darkModeButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent != null ) {
            if("logout".equals(intent.getAction())) {
                logOut();
            }
        }
        setContentView(R.layout.activity_settings);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        darkModeButton = findViewById(R.id.DarkButton);


        darkModeButton.setOnClickListener(v -> {
            loading();
            if(Utils.light == 1) {
                Utils.light = 0;
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            } else {
                Utils.light = 1;
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);


            }
        });


        //log out
        AppCompatImageView logoutButton = findViewById(R.id.logOut);
        logoutButton.setOnClickListener(v -> logOut());

        //back
        AppCompatImageView backButton = findViewById(R.id.imageBackPage);
        backButton.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });

        //   update address

        editTextServerAddress = findViewById(R.id.serverIpInput);
        buttonSave = findViewById(R.id.updateButton);
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String currentAddress = sharedPreferences.getString(KEY_BASE_URL, "");
        editTextServerAddress.setText(currentAddress);

        buttonSave.setOnClickListener(new View.OnClickListener() {



            //TODO: Namma do validations of port and ip - from chatGPT
            // If it is more convenient, you can separate IP and port
            @Override
            public void onClick(View v) {
                String newAddress = editTextServerAddress.getText().toString();
                if(isAddressValid(newAddress)){
                    // Save the new server address to SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_BASE_URL, newAddress);
                    editor.apply();
                    String result = "Address updated✅";
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();

                    // Finish the activity
                    logOut();
                } else {
                    String result = "incorrect Address❗";
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                }


            }
        });



    }


    public boolean isAddressValid(String address) {
        // Check if the address starts with "http://"
        if (!address.startsWith("http://")) {
            return false;
        }

        // Remove the "http://" prefix
        String addressWithoutPrefix = address.substring(7);

        // Split the address into IP and port
        String[] parts = addressWithoutPrefix.split(":");
        if (parts.length != 2) {
            return false; // Invalid address format
        }

        String ip = parts[0];
        String addressWithoutSuffix = parts[1];

        // Check if IP is valid
        if (!isValidIP(ip)) {
            return false;
        }


        if (!addressWithoutSuffix.endsWith("/api/")) {
            return false;
        }
        String port = addressWithoutSuffix.substring(0, addressWithoutSuffix.length() - 5);

        // Check if port is valid
        if (!isValidPort(port)) {
            return false;
        }

        // Address is valid
        return true;
    }

    private boolean isValidIP(String ip) {
        // Regular expression pattern to validate IP address
        String ipPattern = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        return ip.matches(ipPattern);
    }

    private boolean isValidPort(String port) {
        // Convert the port string to an integer
        try {
            int portNumber = Integer.parseInt(port);
            // Check if the port number is within a valid range
            return portNumber > 0 && portNumber <= 65535;
        } catch (NumberFormatException e) {
            return false; // Invalid port number format
        }
    }

    public void clearSharedPreferences() {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_TOKEN);
        editor.remove(KEY_DISPLAY_NAME);
        editor.remove(KEY_USERNAME);
        editor.remove(KEY_PROFILE_PIC);

        editor.apply();
    }

public void clearDB(){
    AppDB db = AppDB.getInstance(this);
    db.chatDao();
    //creates a thread that clears the messages
    Executor executor = Executors.newSingleThreadExecutor();
    executor.execute(() -> {
        db.chatDao().clearChats();
        db.messageDao().clearMessages();
    });
}


    public void logOut() {
        clearSharedPreferences();
        clearDB();

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


    public void loading() {

        clearDB();

        Intent intent = new Intent(this, Loading.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
