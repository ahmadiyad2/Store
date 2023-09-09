package com.example.finalproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.ClipData;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.finalproject.databinding.ActivityAddProductBinding;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class AddProduct extends AppCompatActivity {

    ActivityAddProductBinding binding ;

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
                                                 binding.imageProductItem.setImageBitmap(decodeStream);
                                             } catch (Exception e) {
                                                 Log.e("ex", e.getMessage());
                                             }

                                         }
                                     }
                                 }
                             }
                     );

                      DatabaseHelper dpHelper ;
    byte[] image = null;
    boolean added ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        
        dpHelper = new DatabaseHelper(this);

        binding.Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Prodacts item = null;
                String name = binding.EDnameProduct.getText().toString();
                String price = binding.EDPriceProduct.getText().toString();
                int ps = Integer.parseInt(binding.PSproduct.getText().toString());


                try {

                    BitmapDrawable drawable = (BitmapDrawable) binding.imageProductItem.getDrawable();
                    Bitmap bitmap = drawable.getBitmap();
                    image = getBytes(bitmap);



                       if (binding.imageProductItem.getDrawable() == null){

                        }

                       if (image ==null  || price.isEmpty()|| name.isEmpty()){

                           Toast.makeText(AddProduct.this, "data is nut complite", Toast.LENGTH_SHORT).show();
                       } else {

                           item = new Prodacts(name, Double.parseDouble(price), image ,ps);
                           if (ps>0){
                              item.setProductPs(ps);
                               added = dpHelper.addProduct(item);
                           } else {
                               Toast.makeText(AddProduct.this, "the countity is field ", Toast.LENGTH_SHORT).show();
                           }


                        }




                    if (added) {


                                Intent intent = new Intent(getApplicationContext(), MainActivity2ownner.class);
                                startActivity(intent);
                                finish();



                    } else {
                        Toast.makeText(AddProduct.this, "item is already added", Toast.LENGTH_LONG).show();

                    }
                } catch (Exception ex) {
                    Toast.makeText(AddProduct.this, "Enter valid input and upload an image", Toast.LENGTH_LONG).show();

                }

            }
        });

        binding.LodingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);


                launcher.launch(intent);

            }
        });

    }


    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, stream);
        return stream.toByteArray();
    }
}
