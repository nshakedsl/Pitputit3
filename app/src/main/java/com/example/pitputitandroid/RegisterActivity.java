package com.example.pitputitandroid;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;

import com.example.pitputitandroid.api.UserAPI;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        AppCompatButton registerButton = findViewById(R.id.registerButton);
        TextView alreadyRegistered = findViewById(R.id.alreadyRegistered);
        alreadyRegistered.setOnClickListener(v -> this.finish());

        ImageView addPhoto = (ImageView) findViewById(R.id.imageAdd);
        addPhoto.setImageResource(R.drawable.user);
        EditText nickEdit = findViewById(R.id.editTextNickName);
        EditText userEdit = findViewById(R.id.usernameRegister);
        EditText passEdit = findViewById(R.id.editTextTextPassword);
        EditText verifyPassEdit = findViewById(R.id.editTextTextPasswordagain);
        AppCompatButton addPhotoButton = findViewById(R.id.addPhotoButton);
        registerButton.setOnClickListener(v -> attemptRegister(userEdit, nickEdit,
                passEdit, verifyPassEdit));


        addPhoto.animate().translationY(50).setDuration(700).setStartDelay(0);
        addPhotoButton.animate().translationY(50).setDuration(700).setStartDelay(0);
        registerButton.animate().translationY(50).setDuration(700).setStartDelay(0);
        alreadyRegistered.animate().translationY(50).setDuration(700).setStartDelay(0);
        userEdit.animate().translationY(50).setDuration(700).setStartDelay(0);
        nickEdit.animate().translationY(50).setDuration(700).setStartDelay(0);
        passEdit.animate().translationY(50).setDuration(700).setStartDelay(0);
        verifyPassEdit.animate().translationY(50).setDuration(700).setStartDelay(0);
    }
    //sends to register/signup page

    private void attemptRegister(EditText usernameE, EditText nicknameE,
                                 EditText passwordE, EditText verifyPasswordE) {
        Editable username = usernameE.getText();
        Editable nickname = nicknameE.getText();
        Editable password = passwordE.getText();
        Editable verifyPassword = verifyPasswordE.getText();
        String result;
        String resUsername = isValidUsername(username.toString());
        String resPassword = isValidPassword(password.toString());
        String resNickname = isValidNickname(nickname.toString());
        if (username.toString().equals("") || password.toString().equals("") || nickname.toString().equals("")
                || verifyPassword.toString().equals("")) {
            result = "all fields are mandatory";
        } else if (!resUsername.equals("valid")) {
            result = isValidUsername(resUsername);
        } else if (!resPassword.equals("valid")) {
            result = isValidPassword(resPassword);
    } else if (!resNickname.equals("valid")) {
            result = isValidNickname(resNickname);
        } else if (!password.toString().equals(verifyPassword.toString())) {
            result = "The passwords are not equals";
        } else {


            UserAPI userAPI=new UserAPI(getApplicationContext());
            userAPI.register(username.toString(),password.toString(),nickname.toString(),"image");


            userAPI.getRegisterResult().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean isSuccess) {
                    if (isSuccess) {
                        Log.d("TAG","register success");
                        // Register successful
                        // TODO Ofir! proceed to LoginActivity

                    } else {
                        String result= "This user name is already exist❗";
                        Log.d("TAG","register failed");
                        // Register failed, handle the error
                        // TODO Ofir! Display an error message to the user
                    }
                }
            });

            result = "valid";
        }

        if (!result.equals("valid")) {
            invalidResult(result);
            return;
        }
        registerUser();
    }


    private static String isValidUsername(String username) {
        if (!username.matches("^[a-zA-Z0-9\\._:\\-\\?!]+$")) {
            return "You chose invalid characters";
        } else if (username.length() > 32) {
            return "inputs must contain until 32 characters";
        } else if (username.length() < 2) {
            return "inputs must contain at least 2 characters";
        }
        return "valid";
    }


    private static String isValidPassword(String password) {

        if (!password.matches("^[a-zA-Z0-9\\._:\\-\\?!]+$")) {
            return "You chose invalid characters";
        } else if (!password.matches(".*[0-9].*") || !password.matches(".*[a-z].*")
                || !password.matches(".*[A-Z].*")) {
            return "Password must contain a combination of uppercase and lowercase" +
                    " letters and numbers";
        } else if (password.length() < 8) {
            return "Password must contain at least 8 characters";
        } else if (password.length() > 32) {
            return "inputs must contain until 32 characters";
        }
        return "valid";


    }

    public static String isValidNickname(String nickName) {
        if (!nickName.matches("^[a-zA-Z0-9\\._:\\-\\?!]+$")) {
            return "You chose invalid characters";
        } else if (nickName.length() > 32) {
            return "inputs must contain until 32 characters";
        } else if (nickName.length() < 2) {
            return "inputs must contain at least 2 characters";
        }
        return "valid";
    }

    private void invalidResult(String result) {
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    }

    private void registerUser() {

    }

}
