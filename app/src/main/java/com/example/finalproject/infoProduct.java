package com.example.finalproject;

import static com.example.finalproject.AddProduct.getBytes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.databinding.ActivityInfoProductBinding;

import java.io.ByteArrayOutputStream;

public class infoProduct extends AppCompatActivity {
    ActivityInfoProductBinding binding;
    private int productId;
    public static int numbercuntity = 10;
    byte[] image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInfoProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        String name = intent.getStringExtra("name");
        double price = intent.getDoubleExtra("price", 0.0);
        byte[] image1 = intent.getByteArrayExtra("image");
        int ps = intent.getIntExtra("ps", 1);

        Prodacts product = new Prodacts(name, price, image1, ps);
        binding.psnumber.setText(String.valueOf(numbercuntity));
        product.setIdProduct(id);
        Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
        binding.imageViewInfo.setImageBitmap(bitmap);
        binding.textInfo.setText(product.getNameProduct());
        binding.price2.setText(String.valueOf(product.getPriceProduct()));

        binding.buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), Cart.class);

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(infoProduct.this);
                alertDialog.setTitle("Are your shore in sell ?");
                alertDialog.setMessage("Payment will be made through the card");
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int numberproduct = Integer.parseInt(binding.countity.getText().toString());
                        double price2 = product.getPriceProduct();

                        if (numbercuntity >= 0) {
                            if (numberproduct <= numbercuntity) {
                                try {
                                    double resultcount = (numberproduct * price2);
                                    String r = String.valueOf(resultcount);

                                    int t = Integer.parseInt(binding.countity.getText().toString());
                                     numbercuntity -= t;
                                     binding.psnumber.setText(String.valueOf(numbercuntity));
                                     binding.countity.setText("");


                                       Prodacts item = null;
                                       String name = binding.textInfo.getText().toString();
                                       String price = binding.price2.getText().toString();
                                       int ps = Integer.parseInt(binding.psnumber.getText().toString());
                                       BitmapDrawable drawable = (BitmapDrawable) binding.imageViewInfo.getDrawable();
                                        Bitmap bitmap = drawable.getBitmap();
                                        image = getBytes(bitmap);

                                        if (name.equals("") || price.equals("") || image.length == 0)
                                            throw new Exception("invalid input");
                                        if (!price.isEmpty() && Double.parseDouble(price) <= 0)
                                            throw new Exception("invalid input");

                                        if (binding.imageViewInfo.getDrawable() == null)
                                            throw new Exception("invalid input");

                                        item = new Prodacts(name, Double.parseDouble(price), image ,ps);

                                        item.setProductPriceCount(r);
                                        item.setProductPs(ps);

                                        boolean added = databaseHelper.addProductCart(item);

                                        if (added) {
                                            Intent intent2 = new Intent(getBaseContext() , Cart.class);
                                            startActivity(intent2);
                                            finish();

                                        } else {

                                        }


                                } catch (Exception e) {

                                }
                            } else {
                                Toast.makeText(infoProduct.this, "Countity is not Found ", Toast.LENGTH_SHORT).show();
                            }


                        }
                    }
                });

                alertDialog.setCancelable(false);
                alertDialog.show();
            }
        });
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, stream);
        return stream.toByteArray();
    }
}