package com.example.finalproject;

import static com.example.finalproject.AddProduct.getBytes;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.finalproject.databinding.ActivityEditeProductBinding;

import java.io.InputStream;

public class EditeProduct extends AppCompatActivity {
    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            Uri uri = data.getData();
                            try {
                                InputStream inputStream = getContentResolver().openInputStream(uri);
                                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                                binding.imageViewInfo.setImageBitmap(decodeStream);
                            } catch (Exception e) {
                                Log.e("ex", e.getMessage());
                            }

                        }
                    }
                }
            }
    );

              ActivityEditeProductBinding binding ;
               DatabaseHelper dpHelper ;
                byte[] image = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityEditeProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        dpHelper = new DatabaseHelper(this);



        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        String name = intent.getStringExtra("name");
        double price = intent.getDoubleExtra("price", 0.0);
        byte[] image1 = intent.getByteArrayExtra("image");
        int ps = intent.getIntExtra("ps", 1);

        Prodacts product = new Prodacts(name, price, image1, ps);
        binding.EDContinuityProduct.setText(String.valueOf(ps));
        product.setIdProduct(id);
        Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
        binding.imageViewInfo.setImageBitmap(bitmap);
        binding.EDnameProduct.setText(product.getNameProduct());
        binding.EDPriceProduct.setText(String.valueOf(product.getPriceProduct()));



        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = binding.EDnameProduct.getText().toString();
                double newPrice = Double.parseDouble(binding.EDPriceProduct.getText().toString());
                int newPs = Integer.parseInt(binding.EDContinuityProduct.getText().toString());

                BitmapDrawable drawable = (BitmapDrawable) binding.imageViewInfo.getDrawable();
                Bitmap imageBitmap = drawable.getBitmap();
                byte[] newImage = getBytes(imageBitmap);

                Prodacts updatedProduct = new Prodacts(newName, newPrice, newImage, newPs);
                updatedProduct.setIdProduct(id);

                int rowsAffected = dpHelper.updateProduct(updatedProduct);
                if (rowsAffected > 0) {

                  Intent intent1 = new Intent(getBaseContext() , MainActivity2ownner.class);
                  startActivity(intent1);
                  finish();
                } else {
                    Toast.makeText(EditeProduct.this, "فشل في تحديث البيانات", Toast.LENGTH_SHORT).show();
                }
            }
        });


        binding.uplodeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);


                launcher.launch(intent);
            }
        });


    }
}