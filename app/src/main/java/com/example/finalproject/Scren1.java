package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject.databinding.ActivityScren1Binding;

public class Scren1 extends AppCompatActivity {
      ActivityScren1Binding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScren1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(getBaseContext() , Scand2.class);
                startActivity(intent);
                finish();
            }
        });

        binding.nexticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(getBaseContext() , Scand2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}