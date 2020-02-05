package com.example.restaurantmanagement.OrderActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.RestaurantMenu.MenuInfo;
import com.example.restaurantmanagement.RestaurantMenu.RestaurantMenuActivity;
import com.example.restaurantmanagement.TableManagement.TableAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private DatabaseReference mData, mData2;

    ImageView imgOrderFoodInfo, imgAdd, imgMinus, imgClose;
    TextView txtOrderFoodName, txtOrderFoodDetail, txtNumOrderFood;
    Button btnOrderFoodPrice;

    public static final String FB_STORAGE_FOOD = "Food/";
    public static final String FB_STORAGE_DRINK = "Drink/";
    public static final String FB_DATABASE_FOOD = "Menu/Food";
    public static final String FB_DATABASE_DRINK = "Menu/Drink";

    public int posFood;
    public int type; // 0 là đồ ăn, 1 là thức uống
    public String key;
    String orderPrice = "0";
    ArrayList<MenuInfo> food;
    OrderFoodInfo oderFood;

    int res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        AnhXa();

        Bundle foodBundle = null;
        getBundleFromMenu(foodBundle);
        txtOrderFoodName.setText(food.get(posFood).getFoodName());
        txtOrderFoodDetail.setText(food.get(posFood).getDetail());
        btnOrderFoodPrice.setText(food.get(posFood).getPrice() + "d");
        Picasso.get().load(food.get(posFood).getUrl()).into(imgOrderFoodInfo);

        orderPrice = food.get(posFood).getPrice();
        res = Integer.parseInt(orderPrice);

        mData = FirebaseDatabase.getInstance().getReference();


        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(OrderActivity.this, RestaurantMenuActivity.class);
                startActivity(intent);
            }
        });

        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minusFood();
            }
        });
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFood();
            }
        });
        btnOrderFoodPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Đã thêm vào giỏ hàng!", Toast.LENGTH_SHORT).show();

                oderFood = new OrderFoodInfo(txtOrderFoodName.getText().toString(),
                        food.get(posFood).getPrice(),
                        food.get(posFood).getDetail(),
                        food.get(posFood).getUrl(),
                        txtNumOrderFood.getText().toString(),
                        Integer.toString(res)
                );
                mData.child("Table/" + "tb" + Integer.toString(TableAdapter.pos) + "/ListOder/").push().setValue(oderFood);
                mData2 = FirebaseDatabase.getInstance().getReference("Table/" + "tb" + Integer.toString(TableAdapter.pos) + "/");
                mData2.child("TinhTrang").setValue("Đã order");
                finish();
                Intent intent = new Intent(OrderActivity.this, RestaurantMenuActivity.class);
                startActivity(intent);


            }
        });
    }

    private void minusFood() {
        int temp = Integer.parseInt(txtNumOrderFood.getText().toString());
        if (temp <= 1) return;
        else {
            txtNumOrderFood.setText(String.valueOf(temp - 1));
            res = Integer.parseInt(String.valueOf(temp - 1)) * Integer.parseInt(orderPrice);
            btnOrderFoodPrice.setText("+ " + String.valueOf(res) + "d");

        }
    }

    private void addFood() {

        int temp = Integer.parseInt(txtNumOrderFood.getText().toString());
        txtNumOrderFood.setText(String.valueOf(temp + 1));
        res = Integer.parseInt(String.valueOf(temp + 1)) * Integer.parseInt(orderPrice);
        btnOrderFoodPrice.setText("+ " + String.valueOf(res) + "d");
    }

    private void getBundleFromMenu(Bundle foodBundle) {
        foodBundle = this.getIntent().getExtras();
        posFood = foodBundle.getInt("position");
        type = foodBundle.getInt("type");
        if (type == 0) {
            mStorageRef = FirebaseStorage.getInstance().getReference(FB_STORAGE_FOOD);
            mDatabaseRef = FirebaseDatabase.getInstance().getReference(FB_DATABASE_FOOD);
        } else {
            mStorageRef = FirebaseStorage.getInstance().getReference(FB_STORAGE_DRINK);
            mDatabaseRef = FirebaseDatabase.getInstance().getReference(FB_DATABASE_DRINK);
        }


        food = (ArrayList<MenuInfo>) foodBundle.getSerializable("infoFood");
        key = food.get(posFood).getFoodName();


    }

    private void AnhXa() {
        imgClose = findViewById(R.id.imgCloseOrder);
        imgAdd = findViewById(R.id.imgAdd);
        imgMinus = findViewById(R.id.imgMinus);
        imgOrderFoodInfo = findViewById(R.id.imgFood);
        btnOrderFoodPrice = findViewById(R.id.btnFoodPrice);
        txtOrderFoodName = findViewById(R.id.txtFoodName);
        txtOrderFoodDetail = findViewById(R.id.txtOrderFoodDetail);
        txtNumOrderFood = findViewById(R.id.txtNumberOrderFood);
    }
}
