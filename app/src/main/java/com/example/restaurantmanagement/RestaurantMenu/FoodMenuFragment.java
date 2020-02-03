package com.example.restaurantmanagement.RestaurantMenu;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FoodMenuFragment extends Fragment {
    public String FB_DATABASE_FOOD = "Menu/Food";
    private ArrayList<MenuInfo> menuInfoList = new ArrayList<>();
    RecyclerView revFoodMenu;
    private DatabaseReference mDatabaseRef;
    private ProgressDialog progressDialog;
    public static String FOOD_MENU = "FOOD MENU";

    @androidx.annotation.Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @androidx.annotation.Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.food_menu_fragment, container, false);
        revFoodMenu = (RecyclerView) view.findViewById(R.id.revMenuFood);
        progressDialog = new ProgressDialog(container.getContext());
        progressDialog.setMessage("Vui lòng chờ giây lát...");
        progressDialog.show();

        mDatabaseRef = FirebaseDatabase.getInstance().getReference(FB_DATABASE_FOOD);

        final FoodMenuAdapter foodMenuAdapter = new FoodMenuAdapter(menuInfoList, this);
        revFoodMenu.setAdapter(foodMenuAdapter);
        revFoodMenu.setLayoutManager(new LinearLayoutManager(getContext()));

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //ImageUpload class require default constructor
                    MenuInfo menuInfo = snapshot.getValue(MenuInfo.class);
                    menuInfoList.add(menuInfo);
                }
                foodMenuAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }


}
