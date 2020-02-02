package com.example.restaurantmanagement.TableManagement;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.PayActivity.PayFoodActivity;
import com.example.restaurantmanagement.R;
import com.example.restaurantmanagement.RestaurantMenu.RestaurantMenuActivity;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
    public static int pos;
    List<TableInfo> tableInfos = new ArrayList<>();
    Context context;
    String tableName[] = {"Bàn 01", "Bàn 02", "Bàn 03", "Bàn 04", "Bàn 05", "Bàn 06", "Bàn 07", "Bàn 08", "Bàn 09", "Bàn 10",
            "Bàn 11", "Bàn 12", "Bàn 13", "Bàn 14", "Bàn 15", "Bàn 16", "Bàn 17", "Bàn 18"};
    Boolean selected = false;

    public TableAdapter(Context context) {
        this.context = context;
        for (int i = 0; i < tableName.length; i++) {
            TableInfo tableInfo = new TableInfo(tableName[i], selected);
            tableInfos.add(tableInfo);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.table_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final View view = holder.itemView;
        TextView txtName = view.findViewById(R.id.txtName);
        ImageView imgTable = view.findViewById(R.id.imgTable);
        final int temp = position;
        imgTable.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, view);

                popupMenu.getMenuInflater().inflate(R.menu.menu_table_diagram, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.menuTableSelectFood:
                                Intent intentSelectedFood = new Intent(context, RestaurantMenuActivity.class);
                                pos = temp + 1;
                                context.startActivity(intentSelectedFood);
                                break;
                            case R.id.menuTablePay:
                                Intent intentPay = new Intent(context, PayFoodActivity.class);
                                pos = temp + 1;
                                context.startActivity(intentPay);
                                break;
                            case R.id.menuTableCancel:
                                //  mData = FirebaseDatabase.getInstance().getReference("Table/" + "tb" + Integer.toString(TableDiagramAdapter.pos) + "/ListOder/");
                                //  mData2 = FirebaseDatabase.getInstance().getReference("Table/" + "tb" + Integer.toString(TableDiagramAdapter.pos) + "/");
                                //  cancelTable(view);
                                break;
                        }

                        return false;
                    }
                });
                popupMenu.show();
                Toast.makeText(context, temp + 1 + "", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        TableInfo tableInfo = tableInfos.get(position);
        txtName.setText(tableInfo.getTableName());
    }

    @Override
    public int getItemCount() {
        return tableInfos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
