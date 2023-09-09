package com.example.finalproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.databinding.ActivityProfielBinding;

public class Profiel extends AppCompatActivity {
    ActivityProfielBinding binding ;
    DatabaseHelper helper ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityProfielBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        helper = new DatabaseHelper(this);

        String username = helper.getUserNameFromSomeFunction();

        String name = helper.getJopNameFromUsername(username);
        String userName = helper.getUserNameFromUsername(username);
        String description = helper.getDescriptionFromUsername(username);

        binding.name.setText(name);
        binding.Username.setText(userName);
        binding.discraption.setText(description);



        binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profiel.this, Edite.class);
                intent.putExtra("name", binding.name.getText().toString());
                intent.putExtra("UserName", binding.Username.getText().toString());
                intent.putExtra("discraption", binding.discraption.getText().toString());
                startActivity(intent);

            }
        });


             binding.add.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent = new Intent(getBaseContext() , AddProduct.class);
                     startActivity(intent);
                     finish();
                 }
             });

           String name1 = helper.getJopNameFromUsername(username);
         String userName1 = helper.getUserNameFromUsername(username);
          String description1 = helper.getDescriptionFromUsername(username);

        binding.name.setText(name1);
        binding.Username.setText(userName1);
        binding.discraption.setText(description1);


    }

    @Override
    public void onBackPressed() {

                 Intent intent = new Intent(getBaseContext(), MainActivity2.class);
                 startActivity(intent);
                 finish();
    }
}
