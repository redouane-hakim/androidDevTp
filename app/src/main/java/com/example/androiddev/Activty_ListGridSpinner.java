package com.example.androiddev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activty_ListGridSpinner extends AppCompatActivity {
    Button btn_list;
    Button btn_grid;
    Button btn_spinner;

    Button btn_recycleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activty_list_grid_spinner);

        btn_grid = findViewById(R.id.btn_grid);
        btn_spinner = findViewById(R.id.btn_spinner);
        btn_list = findViewById(R.id.btn_list);
        btn_recycleList = findViewById(R.id.btn_recycleList);

        btn_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activty_ListGridSpinner.this , Activty_Grid.class);
                startActivity(intent);
                finish();
            }
        });
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activty_ListGridSpinner.this , Activity_List.class);
                startActivity(intent);
                finish();
            }
        });
        btn_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activty_ListGridSpinner.this , Activity_Spinner.class);
                startActivity(intent);
                finish();
            }
        });
        btn_recycleList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activty_ListGridSpinner.this , Activity_Recycle_List.class);
                startActivity(intent);
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}