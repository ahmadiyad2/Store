package com.example.finalproject;

import static com.example.finalproject.AddProduct.getBytes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.databinding.ItemProductsBinding;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>  {

    private Context context;
    private int resource;
    private ArrayList<Prodacts> items;





    public ProductAdapter(Context context ,int resource, ArrayList<Prodacts> items) {
        this.context = context;
        this.resource = resource;
        this.items = items;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductsBinding binding = ItemProductsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        Prodacts product = items.get(position);
        holder.name.setText(product.getNameProduct());
        holder.price.setText(String.valueOf(product.getPriceProduct()));
        holder.ps.setText(String.valueOf(product.getProductPs()));
        Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
        holder.phonto.setImageBitmap(bitmap);







        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] image = getBytes(bitmap);
                product.setImage(image);

                Intent intent = new Intent(context, infoProduct.class);
                intent.putExtra("id", product.getIdProduct());
                intent.putExtra("name", product.getNameProduct());
                intent.putExtra("price", product.getPriceProduct());
                intent.putExtra("image", product.getImage());



                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView id ;
        TextView  name  ;
        TextView price  ;

        TextView ps  ;
        ImageView phonto ;




        public ProductViewHolder(ItemProductsBinding binding) {

            super(binding.getRoot());
            name= binding.name;
            ps= binding.ps;
            price=binding.pricePruduct;
            phonto=binding.ImageProduct;




        }
    }



}
