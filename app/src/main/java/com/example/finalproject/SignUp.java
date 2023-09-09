package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.databinding.ActivitySignUpBinding;

public class SignUp extends AppCompatActivity {
           ActivitySignUpBinding binding ;
           DatabaseHelper databaseHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String Username = binding.ed1.getText().toString().trim();
                String email = binding.ed2.getText().toString().trim();

                String password = binding.ed3.getText().toString().trim();
                String password2 = binding.ed4.getText().toString().trim();

                if (Username.isEmpty() || email.isEmpty() || password.isEmpty()  || password2.isEmpty()) {
                    Toast.makeText(SignUp.this, "The data is not Completed", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(password2)) {
                    Toast.makeText(SignUp.this, "The passwords do not match", Toast.LENGTH_SHORT).show();
                } else {


                        DataUserName dataUserName = new DataUserName(Username , email   , password) ;
                        long r = databaseHelper.insertDataUser(dataUserName) ;

                        if(r !=- 1){

                            Intent intent = new Intent(getBaseContext() , Edite.class);
                            startActivity(intent);
                            finish();

                        }else{
                            Toast.makeText(SignUp.this, "error to add info in database ", Toast.LENGTH_SHORT).show();
                       }


                 }




            }
          });

          binding.login.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent  = new Intent(getBaseContext() , Loginapp.class);
                  startActivity(intent);
                  finish();
              }
          });





    }

    public void onBackPressed() {

        Intent intent = new Intent(getBaseContext(), Loginapp.class);
        startActivity(intent);
        finish();
    }
}