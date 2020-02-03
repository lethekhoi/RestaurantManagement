package com.example.restaurantmanagement.RestaurantMenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FoodMenuAdapter extends RecyclerView.Adapter {
    ArrayList<MenuInfo> menuFoodList;
    public static FoodMenuFragment foodMenuFragment;

    public FoodMenuAdapter(ArrayList<MenuInfo> menuInfos, FoodMenuFragment foodMenuFragment) {
        this.menuFoodList = menuInfos;
        this.foodMenuFragment = foodMenuFragment;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.tab_menu_food, parent, false);


        return new MyFoodMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        final MyFoodMenuViewHolder myFoodMenuViewHolder = (MyFoodMenuViewHolder) holder;
        MenuInfo menuInfo = menuFoodList.get(position);
        myFoodMenuViewHolder.txtMenuNameFood.setText(menuInfo.getFoodName());
        myFoodMenuViewHolder.txtMemuPrice.setText(menuInfo.getPrice() + "d");
        Picasso.get().load(menuInfo.getUrl()).into(myFoodMenuViewHolder.imgMenuFood);
        myFoodMenuViewHolder.imgMenuFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent iFood = new Intent(view.getContext(), OrderActivity.class);
//                Bundle bFood = new Bundle();
//                bFood.putSerializable("infoFood", menuFoodList);
//                bFood.putInt("position", holder.getAdapterPosition());
//                bFood.putInt("type", 0);
//                iFood.putExtras(bFood);
//                view.getContext().startActivity(iFood);
//                ((Activity) view.getContext()).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuFoodList.size();
    }

    private class MyFoodMenuViewHolder extends RecyclerView.ViewHolder {
        ImageView imgMenuFood, imgAddFood;
        TextView txtMenuNameFood, txtMemuPrice;

        public MyFoodMenuViewHolder(View view) {
            super(view);
            imgMenuFood = view.findViewById(R.id.imgOrderFood);
            txtMenuNameFood = view.findViewById(R.id.txtMenuFoodName);
            txtMemuPrice = view.findViewById(R.id.txtOrderFoodPrice);
            imgAddFood = view.findViewById(R.id.imgbMenuAddFood);
        }
    }
}
