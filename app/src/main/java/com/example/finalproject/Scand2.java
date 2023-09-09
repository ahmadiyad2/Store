package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject.databinding.ActivityScand2Binding;

public class Scand2 extends AppCompatActivity {
    ActivityScand2Binding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScand2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(getBaseContext() , Loginapp.class);
                startActivity(intent);
                finish();
            }
        });

        binding.nexticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(getBaseContext() , Loginapp.class);
                startActivity(intent);
                finish();
            }
        });
    }
    }
