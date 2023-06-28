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


import com.example.pitputitandroid.R;
import com.example.pitputitandroid.RegisterActivity;

public class SettingsActivity extends AppCompatActivity {

     Switch darkModeSwitch;


    private EditText editTextServerAddress;
    private AppCompatButton buttonSave;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


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
                finish();
            }
        });





        // update dark-mode

        darkModeSwitch=findViewById(R.id.dark_mode_switch);
        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()  {
            @Override
            public void onCheckedChanged( CompoundButton compoundButton, boolean b) {
                if(b){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });

//        darkModeSwitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Handle the click event here
//                boolean isChecked = ((CompoundButton) view).isChecked();
//                if (isChecked) {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                } else {
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                }
//            }
//        });



//        AppCompatButton updateButton = findViewById(R.id.updateButton);
//        EditText serverIpText = findViewById(R.id.serverIpInput);
//        ImageView imglogo = findViewById(R.id.imageLogo);
//        TextView settingsText = findViewById(R.id.textSettings);
//
//        darkModeSwitch = findViewById(R.id.dark_mode_switch);
//
//        darkModeSwitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (darkModeSwitch.isChecked()) {
//                    // Switch to dark mode
//                    mainLayout.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
//                } else {
//                    // Switch to light mode
//                    mainLayout.setBackgroundColor(getResources().getColor(android.R.color.background_light));
//                }
//            }
//        });
//
//
//        updateButton.setOnClickListener(v ->
//                loginClick(serverIpText.getText()));
//        //sends to register/signup page
//        registerText.setOnClickListener(v -> {
//            startActivity(new Intent(this, RegisterActivity.class));
//        });
//
//
//        updateButton.animate().translationY(50).setDuration(700).setStartDelay(0);
//        serverIpText.animate().translationY(50).setDuration(700).setStartDelay(0);
//        imglogo.animate().translationY(50).setDuration(700).setStartDelay(0);
//        settingsText.animate().translationY(50).setDuration(700).setStartDelay(0);


    }
}
