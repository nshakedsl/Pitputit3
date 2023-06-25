package com.example.pitputitandroid;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pitputitandroid.api.UserAPI;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.navigation.ui.AppBarConfiguration;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatButton loginButton = findViewById(R.id.loginButton);
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


        TextView welcomeText = findViewById(R.id.textView);
        welcomeText.animate().translationY(50).setDuration(700).setStartDelay(0);
        passText.animate().translationY(50).setDuration(700).setStartDelay(0);
        editText.animate().translationY(50).setDuration(700).setStartDelay(0);
        registerText.animate().translationY(50).setDuration(700).setStartDelay(0);
        imglogo.animate().translationY(50).setDuration(700).setStartDelay(0);
        loginButton.animate().translationY(50).setDuration(700).setStartDelay(0);

    }

    void loginClick(Editable username, Editable password) {
        Intent I = new Intent(this, ChatsActivity.class);


        UserAPI userAPI=new UserAPI(getApplicationContext());
        userAPI.login(username.toString(),password.toString());
        // Observe the login result using the MutableLiveData returned by getLoginResult()
        userAPI.getLoginResult().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isSuccess) {
                if (isSuccess) {
                    // Login successful, proceed to ChatsActivity
                    //String token = userAPI.getToken();
                    startActivity(I);
                } else {
                    String result= "incorrect password or/and username❗";
                    // Login failed, handle the error
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
//    public static String isValidNickname(String password){
//
//        return "valid";
//    }
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
    */
}