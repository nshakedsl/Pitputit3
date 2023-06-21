package com.example.pitputitandroid;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.pitputitandroid.api.UserAPI;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
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
        String result;
        //if( username.toString(), password.toString())
        String resUsername = isValidUsername(username.toString());
        String resPassword = isValidPassword(password.toString());
        Intent I = new Intent(this, ChatsActivity.class);
//        I.putExtra("username",resUsername);
//        I.putExtra("password",resPassword);

        UserAPI userAPI=new UserAPI();
        userAPI.login(username.toString(),password.toString());

        startActivity(I);
    }

    private static String isValidUsername(String username) {

        return "valid";
    }

    private static String isValidPassword(String password) {

        return "valid";
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