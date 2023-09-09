package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.databinding.ActivityEditeBinding;

public class Edite extends AppCompatActivity {

    String nameNew;
    String userNameNew;
    String discabinNew;

    String name;
    String userName;
    String description;

    ActivityEditeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityEditeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DatabaseHelper databaseHelper = new DatabaseHelper(this);


        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        userName = intent.getStringExtra("UserName");
        description = intent.getStringExtra("discraption");

        binding.ED1.setText(name);
        binding.ED2.setText(userName);
        binding.ED3.setText(description);


        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseHelper.clearUsersTable();


                nameNew = binding.ED1.getText().toString();
                userNameNew = binding.ED2.getText().toString();
                discabinNew = binding.ED3.getText().toString();




                long  result = databaseHelper.insertAdditionalUserData(nameNew, userNameNew, discabinNew);


                      if (result != -1) {
                       binding.loding.setIndeterminate(true);
                      binding.ED2.setFocusable(false);
                     binding.ED1.setFocusable(false);
                    binding.ED3.setFocusable(false);

                            Intent intent1 = new Intent(Edite.this, ProfielCustamer.class);
                            startActivity(intent1);
                            finish();

                } else {
                    Toast.makeText(Edite.this, "An error of save the data", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}

