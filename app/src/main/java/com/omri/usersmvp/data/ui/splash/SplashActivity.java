package com.omri.usersmvp.data.ui.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.omri.usersmvp.R;
import com.omri.usersmvp.data.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler myHandler = new Handler();
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start login activity
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                // close self
                finish();
            }
        }, 2000);
    }
}
