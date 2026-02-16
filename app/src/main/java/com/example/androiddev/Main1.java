package com.example.androiddev;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Main1 extends AppCompatActivity {
    Button btn_act1;
    Button btn_act2;
    Button btn_actChiffre;
    Button btn_actZoo;
    Button btn_actVibration;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main1);

        btn_act1 = findViewById(R.id.act1);
        btn_act2 = findViewById(R.id.act2);
        btn_actChiffre= findViewById(R.id.act_chiffre);
        btn_actZoo=findViewById(R.id.act_Zoo);
        btn_actVibration = findViewById(R.id.act_vibration);

        btn_act1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main1.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_act2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main1.this , MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });
        btn_actChiffre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main1.this , Activity_chiffre.class);
                startActivity(intent);
                finish();
            }
        });
        btn_actZoo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main1.this , Activity_Zoo.class);
                startActivity(intent);
                finish();
            }
        });
        btn_actVibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main1.this , Activity_vibration.class);
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