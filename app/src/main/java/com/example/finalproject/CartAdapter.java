    package com.example.finalproject;


    import android.app.AlertDialog;
    import android.content.Context;
    import android.content.DialogInterface;
    import android.graphics.Bitmap;
    import android.graphics.BitmapFactory;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import com.example.finalproject.databinding.ItemProductCartBinding;
    import com.example.finalproject.databinding.ItemProductsBinding;

    import java.util.ArrayList;


    public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

        private Context context;
        private int resource;
        private ArrayList<Prodacts> items;




        public CartAdapter(Context context ,int resource, ArrayList<Prodacts> items ) {
            this.context = context;
            this.resource = resource;
            this.items = items;

        }



        @NonNull
        @Override
        public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemProductCartBinding binding = ItemProductCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new CartAdapter.CartViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

            Prodacts product = items.get(position);
            holder.name.setText(product.getNameProduct());
            holder.price.setText(String.valueOf(product.getPriceProduct()));

            Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
            holder.phonto.setImageBitmap(bitmap);

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
                                    dbHelper.deleteProductCart(product.getIdProduct());
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





        public class CartViewHolder  extends RecyclerView.ViewHolder {

            TextView id ;
            TextView  name  ;
            TextView price  ;
            ImageView phonto ;

            ImageView deleted ;



             public CartViewHolder(ItemProductCartBinding binding  ){
                 super(binding.getRoot());

                 name= binding.name;
                 price=binding.pricePruduct;
                 phonto=binding.ImageProduct;
                 deleted=binding.deleted;

             }
         }



        }



