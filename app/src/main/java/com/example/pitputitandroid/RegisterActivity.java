package com.example.pitputitandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.net.Uri;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;


import com.example.pitputitandroid.api.UserAPI;

import java.io.ByteArrayOutputStream;

public class RegisterActivity extends AppCompatActivity {


    
    private static final int REQUEST_IMAGE_PICK = 1;
    private Bitmap uploadedBitmap;
    ImageView addPhoto;
    private AppCompatButton buttonUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        //find all the elements:
        AppCompatButton registerButton = findViewById(R.id.registerButton);
        TextView alreadyRegistered = findViewById(R.id.alreadyRegistered);
        addPhoto = (ImageView) findViewById(R.id.imageAdd);
        buttonUpload = findViewById(R.id.button_upload);
        EditText nickEdit = findViewById(R.id.editTextNickName);
        EditText userEdit = findViewById(R.id.usernameRegister);
        EditText passEdit = findViewById(R.id.editTextTextPassword);
        EditText verifyPassEdit = findViewById(R.id.editTextTextPasswordagain);

        addPhoto.setImageResource(R.drawable.user);

        alreadyRegistered.setOnClickListener(v -> this.finish());

        AppCompatImageView settingButton = findViewById(R.id.imageSettings);
        settingButton.setOnClickListener(v -> {
            startActivity(new Intent(this, SettingsActivity.class));
        });




        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });


        registerButton.setOnClickListener(v -> attemptRegister(userEdit, nickEdit,
                passEdit, verifyPassEdit));


        animationMove(addPhoto, buttonUpload, registerButton, alreadyRegistered, userEdit,
                nickEdit, passEdit, verifyPassEdit);



    }
    public String castToString(Bitmap bitmapPic){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapPic.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return encodedImage;
    }
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_PICK);
    }

//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
//     super.onActivityResult(requestCode, resultCode, data);
//
//     if(resultCode==RESULT_OK){
//         if(requestCode==GALLERY_REQ_CODE){
//             //the gallery
//             addPhoto.setImageURI(data.getData());
//
//         }
//     }
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                addPhoto.setImageBitmap(bitmap);

                // After setting the bitmap to the ImageView
                Bitmap circularBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(circularBitmap);
                Paint paint = new Paint();
                paint.setShader(new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
                paint.setAntiAlias(true);
                float radius = bitmap.getWidth() > bitmap.getHeight() ? bitmap.getHeight() / 2f : bitmap.getWidth() / 2f;
                canvas.drawCircle(bitmap.getWidth() / 2f, bitmap.getHeight() / 2f, radius, paint);


                uploadedBitmap = circularBitmap;
                addPhoto.setImageBitmap(circularBitmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void animationMove(ImageView addPhoto, AppCompatButton addPhotoButton, AppCompatButton registerButton,
                               TextView alreadyRegistered, EditText userEdit, EditText nickEdit,
                               EditText passEdit, EditText verifyPassEdit) {
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
        } else if(uploadedBitmap == null){
            result = "Image is a mandatory field";
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
            userAPI.register(username.toString(),password.toString(),nickname.toString(),castToString(uploadedBitmap));

            Activity context = this;
            userAPI.getRegisterResult().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean isSuccess) {
                    if (isSuccess) {
                        Log.d("TAG","register success");
                        // Register successful
                        context.finish();

                    } else {
                        String result= "This user name is already exist❗";
                        Log.d("TAG","register failed");
                        // Register failed, handle the error
                        invalidResult(result);
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
