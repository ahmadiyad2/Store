package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.finalproject.databinding.ActivityMain2Binding;
import com.example.finalproject.databinding.ActivityMainActivity2ownnerBinding;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity2ownner extends AppCompatActivity implements SearchView.OnQueryTextListener     {
    ActivityMainActivity2ownnerBinding binding;
    ArrayList<Prodacts> prodacts;
    DatabaseHelper dbhelper;
    ProductAdapterAdmin adapter;
    private MeowBottomNavigation bottomNavigation;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainActivity2ownnerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        prodacts = new ArrayList<>();

        ArrayList<SlideModel> image = new ArrayList<>();
        image.add(new SlideModel(R.drawable.images1, null));
        image.add(new SlideModel(R.drawable.images2, null));
        image.add(new SlideModel(R.drawable.images3, null));
        binding.imageSlider.setSlideAnimation(AnimationTypes.ZOOM_OUT);
        binding.imageSlider.setImageList(image, ScaleTypes.CENTER_CROP);

        dbhelper = new DatabaseHelper(this);
        binding.recicelsr.setLayoutManager(new LinearLayoutManager(this));

        prodacts = dbhelper.getAllProducts();
        adapter = new ProductAdapterAdmin(this, R.layout.activity_add_product, prodacts);
        binding.recicelsr.setAdapter(adapter);

        MaterialToolbar toolbar = binding.TopAppBares;
        DrawerLayout layout = binding.drawerlayout;
        NavigationView navigationView = binding.navitionView;

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.openDrawer(GravityCompat.START);
            }
        });



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                layout.closeDrawer(GravityCompat.START);

                switch (id) {
                    case R.id.nav_home:
                        Intent intent = new Intent(getBaseContext(), Profiel.class);
                        startActivity(intent);
                        break;

                    case R.id.setting:
                        Toast.makeText(com.example.finalproject.MainActivity2ownner.this, "setting is Cliked ", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.sher:
                        Toast.makeText(com.example.finalproject.MainActivity2ownner.this, "Search is Cliked ", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.Stare:
                        Toast.makeText(com.example.finalproject.MainActivity2ownner.this, "Assessment is Cliked ", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.Logout:
                        Intent intent1 = new Intent(getBaseContext() , Loginapp.class);
                        startActivity(intent1);
                        finish();
                        break;

                    case R.id.share:
                        Toast.makeText(com.example.finalproject.MainActivity2ownner.this, "share is Cliked ", Toast.LENGTH_SHORT).show();
                        return true;
                }

                return true;
            }
        });

        bottomNavigation = findViewById(R.id.bottomNavigation);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_baseline_person_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_shopping_cart_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_home_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.baseline_smart_display_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.baseline_star_rate_24));
        bottomNavigation.show(3, true);

        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 1:
                        Intent intent = new Intent(getBaseContext(), Profiel.class);
                        startActivity(intent);
                        finish();
                        break;
                    case 2:
                        Toast.makeText(com.example.finalproject.MainActivity2ownner.this, "is not Customer", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(com.example.finalproject.MainActivity2ownner.this, "home Screen", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(com.example.finalproject.MainActivity2ownner.this, "smart Screen", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(com.example.finalproject.MainActivity2ownner.this, "star+ Screen", Toast.LENGTH_SHORT).show();
                        break;
                }
                return null;
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        MenuItem item = menu.findItem(R.id.menu_add_2);

        View vs = item.getActionView();
        TextView tv_11 = vs.findViewById(R.id.tv_3);
        tv_11.setText("13");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.RED:
                Toast.makeText(this, "red", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.BLUE:
                Toast.makeText(this, "BLUE", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.blake:
                Toast.makeText(this, "blake", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

}

