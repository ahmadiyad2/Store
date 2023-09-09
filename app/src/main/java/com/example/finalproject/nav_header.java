package com.example.finalproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.databinding.ActivitySignUpBinding;
import com.example.finalproject.databinding.NavHeaderBinding;

public class nav_header extends AppCompatActivity {

       NavHeaderBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= NavHeaderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String username = databaseHelper.getUserNameFromSomeFunction();


        String name = databaseHelper.getJopNameFromUsername(username);
        String userName = databaseHelper.getUserNameFromUsername(username);


        binding.name.setText(name);
        binding.Username.setText(userName);



}

}
