package com.infnet.ads.projetodebloco.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.infnet.ads.projetodebloco.R;

public class SplashScreenActivity extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startLogInActivity();
            }
        }, 1000);

    }

    public void startLogInActivity() {
        Intent logInActivity = new Intent(this, LogInActivity.class);
        startActivity(logInActivity);
    }
}
