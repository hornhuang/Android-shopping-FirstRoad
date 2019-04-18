package com.example.firstroad.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.example.firstroad.R;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(1500);

        ImageView imageView = findViewById(R.id.start_page_image);
        imageView.startAnimation(alphaAnimation);

        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(SPLASH_LENGTH);
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish();
            }
        }.start();

    }
}
