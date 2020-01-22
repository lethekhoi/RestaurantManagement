package com.example.restaurantmanagement.TableManagement;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restaurantmanagement.R;

import java.util.ArrayList;
import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

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
                Toast.makeText(context, "AAAA", Toast.LENGTH_SHORT).show();
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
