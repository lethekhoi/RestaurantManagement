package com.example.restaurantmanagement.PayActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.TableManagement.TableAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PayFoodActivity extends AppCompatActivity {
    private DatabaseReference mData;
    private DatabaseReference mData2;
    private ProgressDialog progressDialog;
    private ArrayList<PayFoodInfo> payFoodInfoArrayList;
    Button btnPay;
    RecyclerView recyclerView;
    TextView txtPaySumPrice;

    public static int totalPrice = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_food);
        AnhXa();

        payFoodInfoArrayList = new ArrayList<>();

        progressDialog = new ProgressDialog(PayFoodActivity.this);
        progressDialog.setMessage("Vui lòng chờ giây lát...");
        progressDialog.show();

        mData = FirebaseDatabase.getInstance().getReference("Table/" + "tb" + Integer.toString(TableAdapter.pos) + "/ListOder/");
        mData2 = FirebaseDatabase.getInstance().getReference("Table/" + "tb" + Integer.toString(TableAdapter.pos) + "/");

        final PayFoodAdapter payFoodAdapter = new PayFoodAdapter(payFoodInfoArrayList);
        recyclerView.setAdapter(payFoodAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressDialog.dismiss();

                //Reset lại giá trị tổng tiền cần thanh toán mỗi khi load lại dữ liệu trên database
                totalPrice = 0;
                //

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //ImageUpload class require default constructor

                    PayFoodInfo payFoodInfo = snapshot.getValue(PayFoodInfo.class);

                    payFoodInfoArrayList.add(payFoodInfo);

                    totalPrice = totalPrice + Integer.parseInt(payFoodInfo.getTotal().toString());
                }

                txtPaySumPrice.append(Integer.toString(totalPrice) + "d");

                payFoodAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });

        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payFood();
            }
        });


    }

    private void payFood() {



    }


    private void AnhXa() {
        recyclerView = findViewById(R.id.rycListPay);
        txtPaySumPrice = findViewById(R.id.txtTotalPay);
        btnPay = findViewById(R.id.btnPay);
    }

    private void Processing() {
        progressDialog = new ProgressDialog(PayFoodActivity.this);
        progressDialog.setMessage("Vui lòng chờ giây lát...");
        progressDialog.show();
    }
}
