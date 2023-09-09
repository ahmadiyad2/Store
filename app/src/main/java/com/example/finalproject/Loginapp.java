package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.databinding.ActivityLoginappBinding;
import com.example.finalproject.databinding.ActivityMain2Binding;

public class Loginapp extends AppCompatActivity {
     DatabaseHelper helper;
    ActivityLoginappBinding binding ;
    public static final String andmin = "admin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginappBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        helper = new DatabaseHelper(this);





                    binding.account.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), SignUp.class);
                            startActivity(intent);
                            finish();


                        }
                    });


                    binding.SignUp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getBaseContext(), SignUp.class);
                            startActivity(intent);
                            finish();

                        }
                    });

                     int e = 1 ;


         binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Email = binding.ED1log.getText().toString().trim();
                String password = binding.ED2log.getText().toString().trim();
                boolean r = Email.equals(andmin) && password.equals(andmin) ;

                if (Email.isEmpty() || password.isEmpty()) {

                    Toast.makeText(Loginapp.this, "Please enter username and password", Toast.LENGTH_SHORT).show();

                } else if (r){
                    Intent intent = new Intent(getBaseContext() , MainActivity2ownner.class);
                    startActivity(intent);
                    finish();
                } else if (!r){
                    if (helper.checkLogin(Email, password)) {
                        Intent intent = new Intent(getBaseContext(), MainActivity2.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Loginapp.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }


                }

            }
            });


        }

     public void onBackPressed() {
        finish();
     }

    }
