package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.finalproject.databinding.ActivityProfielCustamerBinding;

public class ProfielCustamer extends AppCompatActivity {

    ActivityProfielCustamerBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding  =ActivityProfielCustamerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        String username = databaseHelper.getUserNameFromSomeFunction();

        String name = databaseHelper.getJopNameFromUsername(username);
        String userName = databaseHelper.getUserNameFromUsername(username);
        String description = databaseHelper.getDescriptionFromUsername(username);

        binding.name.setText(name);
        binding.Username.setText(userName);
        binding.discraption.setText(description);



        binding.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfielCustamer.this, Edite.class);
                intent.putExtra("name", binding.name.getText().toString());
                intent.putExtra("UserName", binding.Username.getText().toString());
                intent.putExtra("discraption", binding.discraption.getText().toString());
                startActivity(intent);
                finish();
            }
        });





    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getBaseContext(), MainActivity2.class);
        startActivity(intent);
        finish();
    }
}
