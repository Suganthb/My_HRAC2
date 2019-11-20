package com.example.my_hrac;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatActivity;

import javax.annotation.Nullable;

public class SplashActivity extends AppCompatActivity {

    private ImageView logo;
    private static int splashTimeOut=5000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstaceState){
        super.onCreate(savedInstaceState);
        setContentView(R.layout.activity_splash);

        logo=(ImageView)findViewById(R.id.logo);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this,BookView.class);
                startActivity(i);
                finish();
            }
        },splashTimeOut);

        Animation myanim = AnimationUtils.loadAnimation(this,R.anim.mysplashanimation);
        logo.startAnimation(myanim);

    }
}
