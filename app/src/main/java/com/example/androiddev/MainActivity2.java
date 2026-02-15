package com.example.androiddev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity2 extends AppCompatActivity {

    Button retour;
    Button add;
    Button minus;
    Button multiply;
    Button division;
    Button clear;

    TextInputEditText txt1;
    TextInputEditText txt2;

    TextView res;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        retour = findViewById(R.id.retour);
        add = findViewById(R.id.add);
        minus  = findViewById(R.id.minus);
        multiply = findViewById(R.id.multiply);
        division = findViewById(R.id.division);
        clear = findViewById(R.id.clear);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        res = findViewById(R.id.res);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = String.valueOf(txt1.getText());
                String text2 = String.valueOf(txt2.getText());
                Number resultat =Integer.parseInt(text1) +Integer.parseInt(text2)  ;
                res.setText(resultat.toString());
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = String.valueOf(txt1.getText());
                String text2 = String.valueOf(txt2.getText());
                Number resultat =Integer.parseInt(text1) - Integer.parseInt(text2)  ;
                res.setText(resultat.toString());
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = String.valueOf(txt1.getText());
                String text2 = String.valueOf(txt2.getText());
                Number resultat =Integer.parseInt(text1) * Integer.parseInt(text2)  ;
                res.setText(resultat.toString());
            }
        });
        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String decimalString = "";
                String text1 = String.valueOf(txt1.getText());
                String text2 = String.valueOf(txt2.getText());
                if(!text2.equals("0")){
                float reste = Float.parseFloat(text1) % Float.parseFloat(text2);
                if (reste!=0){
                    float decimalValue = reste / Float.parseFloat(text2);
                    decimalString = String.valueOf(decimalValue).substring(1);
                }
                Long resultat =Long.parseLong(text1) / Long.parseLong(text2)  ;
                String resultatString = resultat + decimalString;
                res.setText(resultatString);
                return;}
                res.setText("NAN");

            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt1.setText("");
                txt2.setText("");
                res.setText("");
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this , Main1.class);
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