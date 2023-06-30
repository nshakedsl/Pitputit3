package com.example.pitputitandroid;


import static com.example.pitputitandroid.PitputitAndroid.context;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;

import android.widget.EditText;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pitputitandroid.api.UserAPI;
import com.example.pitputitandroid.entities.UserFull;
import com.google.android.gms.tasks.OnSuccessListener;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {


    private String token;

    // Declare the launcher at the top of your Activity/Fragment:
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that that your app will not show notifications.
                }
            });

    private void askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //fireBase

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(MainActivity.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                token = instanceIdResult.getToken();
            }
        });

        askNotificationPermission();

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

                    UserAPI userAPI=new UserAPI(getApplicationContext());
                    userAPI.saveToken(userAPI.getUsername(),token);
                    startActivity(I);

                } else {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }



}