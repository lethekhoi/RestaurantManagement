package com.example.restaurantmanagement.PayActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.TableManagement.TableActivity;
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


//                if (totalPrice == '0') {
//                    final AlertDialog.Builder builder = new AlertDialog.Builder(PayFoodActivity.this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
//                    builder.setMessage("Bạn chưa chọn món ăn")
//                            .setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialogInterface, int i) {
//                                    return;
//                                }
//                            });
//                } else {
//                    payFood();
//                }

                if ((totalPrice == 0)) {
                    trolai();
                } else {
                    payFood();
                }

            }
        });


    }

    private void trolai() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(PayFoodActivity.this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);

        builder.setMessage("Bạn chưa chọn món ăn")
                .setNegativeButton("Trở lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
        builder.show();
    }

    private void payFood() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(PayFoodActivity.this, R.style.Theme_AppCompat_DayNight_Dialog_Alert);

        builder.setMessage("Bạn có chắc muốn thanh toán?")
                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mData.setValue(null);
                        mData2.child("TinhTrang").setValue("Trống");

                        Toast.makeText(getApplicationContext(), "Thanh toán thành công", Toast.LENGTH_SHORT).show();

                        finish();

                        Intent intent = new Intent(PayFoodActivity.this, TableActivity.class);
                        startActivity(intent);

                        totalPrice = 0;
                    }
                })
                .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
        builder.show();
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
