package com.example.restaurantmanagement.TableManagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.restaurantmanagement.MainActivity;
import com.example.restaurantmanagement.R;

import java.util.Objects;

public class TableActivity extends AppCompatActivity {
    private RecyclerView rvTableDiagram;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        AnhXa();
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TableActivity.this, MainActivity.class));
            }
        });

        TableAdapter tableDiagramAdapter = new TableAdapter(this);
        rvTableDiagram.setAdapter(tableDiagramAdapter);
        rvTableDiagram.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

    }

    private void AnhXa() {
        rvTableDiagram = (RecyclerView) findViewById(R.id.rvTable);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
    }
}
