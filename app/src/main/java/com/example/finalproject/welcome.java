package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.finalproject.databinding.ActivityWelcomeBinding;

public class welcome extends AppCompatActivity {

       ActivityWelcomeBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), Scren1.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

    }
}