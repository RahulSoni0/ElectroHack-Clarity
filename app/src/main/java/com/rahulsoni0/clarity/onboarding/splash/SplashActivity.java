package com.rahulsoni0.clarity.onboarding.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.rahulsoni0.clarity.databinding.ActivitySplashBinding;
import com.rahulsoni0.clarity.onboarding.auth.AuthActivity;
import com.rahulsoni0.clarity.ui.activities.MainActivity;
import com.rahulsoni0.clarity.utils.Storage;

public class SplashActivity extends AppCompatActivity {
    private ActivitySplashBinding binding;
    private Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        storage = new Storage(this);

        if (storage.isNewUser()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(SplashActivity.this, AuthActivity.class);
                    startActivity(i);
                    finish();
                }
            },2900);

        } else if (!storage.isNewUser()) {
            if (!storage.isLogin()) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(SplashActivity.this, AuthActivity.class);
                        startActivity(i);
                        finish();
                    }
                },2900);

            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                },2900);

            }

        }


    }
}