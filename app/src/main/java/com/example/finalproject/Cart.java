package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.finalproject.databinding.ActivityCartBinding;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Cart extends AppCompatActivity  {
        ActivityCartBinding binding;
        DatabaseHelper dbhelper;
        ArrayList<Prodacts> prodactsCart ;
        private Context context;
        CartAdapter adapter;
        public static  int countFinal = 0 ;
    private MeowBottomNavigation bottomNavigation;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityCartBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());
            prodactsCart = new ArrayList<>();




            dbhelper = new DatabaseHelper(this);
            binding.reciclare.setLayoutManager(new LinearLayoutManager(this));

            prodactsCart = dbhelper.getAllProductsCart();
            adapter = new CartAdapter( this ,R.layout.activity_add_product ,prodactsCart );
            binding.reciclare.setAdapter(adapter);













            bottomNavigation = findViewById(R.id.bottomNavigation);
            bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_person_24));
            bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_shopping_cart_24));
            bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_home_24));
            bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.baseline_smart_display_24));
            bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.baseline_star_rate_24));
            bottomNavigation.show(2, true);

            bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
                @Override
                public Unit invoke(MeowBottomNavigation.Model model) {

                    switch (model.getId()) {

                        case 1:
                            Intent intent = new Intent(getBaseContext() , Profiel.class);
                            startActivity(intent);
                            finish();
                            break;

                        case 2:

                            break;
                        case 3:
                            Intent intent3 = new Intent(getBaseContext() , MainActivity2.class);
                            startActivity(intent3);
                            finish();
                            break;
                        case 4:
                            Toast.makeText(Cart.this, "smart Screen", Toast.LENGTH_SHORT).show();
                            break;

                        case 5:
                            Toast.makeText(Cart.this, "star+ Screen", Toast.LENGTH_SHORT).show();
                            break;
                    }


                    return null;


                }
            });
            double totalSum = dbhelper.sumColumnValues();
               binding.sell.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   AlertDialog.Builder builder = new AlertDialog.Builder(Cart.this);
                   builder.setTitle("Purchase made")
                           .setMessage("The count is    "+totalSum+" ")
                           .setIcon(R.drawable.baseline_shopping_cart_24)
                           .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                               @Override
                               public void onClick(DialogInterface dialog, int which) {
                                   dbhelper.clearCartTable();
                                   prodactsCart.clear();
                                   adapter.notifyDataSetChanged();
                                   Intent intent1 = new Intent(getBaseContext(), MainActivity2.class);
                                   startActivity(intent1);
                                   finish();
                               }
                           })
                           .setNegativeButton("No", null)
                           .show();


               }

           });


            



        }

      public void onBackPressed() {

        Intent intent = new Intent(getBaseContext(), MainActivity2.class);
        startActivity(intent);
        finish();
    }




}