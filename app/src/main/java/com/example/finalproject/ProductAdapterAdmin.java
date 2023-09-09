    package com.example.finalproject;

    import static com.example.finalproject.AddProduct.getBytes;

    import android.app.AlertDialog;
    import android.content.Context;
    import android.content.DialogInterface;
    import android.content.Intent;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.finalproject.databinding.ItemProductCartBinding;
    import com.example.finalproject.databinding.ItemProductCustamerBinding;
    import com.example.finalproject.databinding.ItemProductsBinding;

    import java.util.ArrayList;

    public class ProductAdapterAdmin extends RecyclerView.Adapter<ProductAdapterAdmin.ProductAdminViewHloder> {
        private Context context;
        private int resource;


        private ArrayList<Prodacts> items;

        public ProductAdapterAdmin( Context context , int resource ,ArrayList<Prodacts> items) {
            this.items = items;
            this.context = context ;

            this.resource = resource;
        }


        @NonNull
        @Override
        public ProductAdminViewHloder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemProductCustamerBinding binding = ItemProductCustamerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ProductAdminViewHloder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductAdminViewHloder holder, int position) {

            Prodacts product = items.get(position);
            holder.name.setText(product.getNameProduct());

            holder.price.setText(String.valueOf(product.getPriceProduct()));

            Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
            holder.phonto.setImageBitmap(bitmap);

            holder.Ediet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                   byte[] image = getBytes(bitmap);
                     product.setImage(image);

                      Intent intent = new Intent(context , EditeProduct.class);
                      intent.putExtra("id", product.getIdProduct());
                      intent.putExtra("name", product.getNameProduct());
                      intent.putExtra("price", product.getPriceProduct());
                      intent.putExtra("image", product.getImage());
                      context.startActivity(intent);

                }
            });

            holder.deleted.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseHelper dbHelper = new DatabaseHelper(context);


                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Confirmation of deletion")
                            .setMessage("Are you sure you want to delete this item?")
                            .setIcon(R.drawable.baseline_delete_24)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dbHelper.deleteProductadmin(product.getIdProduct());
                                    items.remove(position);
                                    notifyItemRemoved(position);
                                    notifyItemRangeChanged(position, items.size());
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();

                }
            });







        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public class ProductAdminViewHloder extends RecyclerView.ViewHolder{

            TextView id ;


            TextView  name  ;
            TextView price  ;
            ImageView phonto ;
            ImageView Ediet ,deleted;



            public ProductAdminViewHloder(ItemProductCustamerBinding binding) {
                super(binding.getRoot());
                name= binding.name;

                price=binding.pricePruduct;
                phonto = binding.ImageProduct;
                Ediet=binding.edite;
                deleted=binding.delete;

            }
        }
    }
