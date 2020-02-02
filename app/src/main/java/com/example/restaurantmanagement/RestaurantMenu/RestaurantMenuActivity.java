package com.example.restaurantmanagement.RestaurantMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.restaurantmanagement.PayActivity.PayFoodActivity;
import com.example.restaurantmanagement.R;

import java.util.Objects;

public class RestaurantMenuActivity extends AppCompatActivity {
    ViewPager viewPager;
    TextView txtCart;
    Toolbar toolbar_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_menu);
        AnhXa();
        setSupportActionBar(toolbar_menu);
        Objects.requireNonNull(getSupportActionBar());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar_menu.setTitle("Thực đơn");
        toolbar_menu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        txtCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(RestaurantMenuActivity.this, PayFoodActivity.class);
                startActivity(intent);
            }
        });
    }

    private void AnhXa() {
        toolbar_menu = findViewById(R.id.menutoolbar);
        viewPager = findViewById(R.id.viewPagerMenu);
        txtCart = findViewById(R.id.txtCart);
    }
}
