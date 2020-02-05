package com.example.restaurantmanagement.PayActivity;

import android.content.Context;
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

public class PayFoodAdapter extends RecyclerView.Adapter {
    ArrayList<PayFoodInfo> payFoodInfoList = new ArrayList<>();
    Context context;

    public PayFoodAdapter(ArrayList<PayFoodInfo> payFoodInfoList) {
        this.payFoodInfoList = payFoodInfoList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.tab_pay_food, viewGroup, false);
        return new MyPayFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyPayFoodViewHolder holder = (MyPayFoodViewHolder) viewHolder;

        PayFoodInfo payFoodInfo = payFoodInfoList.get(i);
       /* holder.imgFood.setImageResource(payFoodInfo.getImgFood());
        holder.txtFoodName.setText(payFoodInfo.getFoodName());
        holder.txtCount.setText("Số lượng: " + payFoodInfo.getCount());
        holder.txtPrice.setText(payFoodInfo.getPrice());
        holder.txtSumEachFood.setText(payFoodInfo.getSum()+"d");*/


        holder.txtFoodName.setText(payFoodInfo.getFoodName());
        holder.txtCount.setText("Số lượng: " + payFoodInfo.getCount());
        holder.txtPrice.setText(payFoodInfo.getPrice());
        holder.txtSumEachFood.setText(payFoodInfo.getTotal() + "d");

        Picasso.get().load(payFoodInfo.getUrl()).into(holder.imgFood);
    }

    @Override
    public int getItemCount() {
        return payFoodInfoList.size();
    }

    private class MyPayFoodViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFood;
        TextView txtFoodName, txtSumEachFood, txtCount, txtPrice;

        public MyPayFoodViewHolder(View view) {
            super(view);
            imgFood = view.findViewById(R.id.imgPayFood);
            txtFoodName = view.findViewById(R.id.txtPayFood);
            txtSumEachFood = view.findViewById(R.id.txtSumEachFood);
            txtCount = view.findViewById(R.id.txtFoodNumber);
            txtPrice = view.findViewById(R.id.txtOrderFoodPrice);
        }
    }
}
