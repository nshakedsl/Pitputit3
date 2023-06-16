package com.example.pitputitandroid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;




public class SplashScreen extends AppCompatActivity {

    TextView appname;
    LottieAnimationView lottie;
    ImageView imgLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        //appname=findViewById(R.id.textSplash);
        lottie=findViewById(R.id.lottie);
        imgLog=findViewById(R.id.imageLogo);



        //appname.animate().translationY(-1400).setDuration(2700).setStartDelay(0);
        imgLog.animate().translationY(-600).setDuration(2700).setStartDelay(0);
        lottie.animate().translationX(2000).setDuration(2000).setStartDelay(2900);



        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        }, 5000) ;

    }
}