package com.example.quizigti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSplash();
    }

    private void getSplash() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        Boolean firstLogin = preferences.getBoolean("FIRST", true);
        if (firstLogin) {
            editor.putBoolean("FIRST", false);
            editor.commit();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                }
            }, 1000);
        }else {
            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
        }
    }
}