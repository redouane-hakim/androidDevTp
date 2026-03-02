package com.example.androiddev;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class Activty_Grid extends AppCompatActivity {
    ArrayList<String> paysGrid ;
    GridView grid;
    Button btn_retour3;

    int selectPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activty_grid);

        String[] paysArray = {
                "France", "Italie", "Allemagne", "Espagne", "Suède",
                "Maroc", "Sénégal", "Égypte", "Afrique du Sud", "Japon",
                "Inde", "Thaïlande","France", "Italie", "Allemagne", "Espagne", "Suède",
                "Maroc", "Sénégal", "Égypte", "Afrique du Sud", "Japon",
                "Inde", "Thaïlande","France", "Italie", "Allemagne", "Espagne", "Suède",
                "Maroc", "Sénégal", "Égypte", "Afrique du Sud", "Japon",
                "Inde", "Thaïlande","France", "Italie", "Allemagne", "Espagne", "Suède",
                "Maroc", "Sénégal", "Égypte", "Afrique du Sud", "Japon",
                "Inde", "Thaïlande","France", "Italie", "Allemagne", "Espagne", "Suède",
                "Maroc", "Sénégal", "Égypte", "Afrique du Sud", "Japon",
                "Inde", "Thaïlande"
        };
        paysGrid = new ArrayList<>(Arrays.asList(paysArray));

        grid = findViewById(R.id.grid);
        Button btnAdd = findViewById(R.id.btn_add);
        Button btnDelete = findViewById(R.id.btn_delete);
        Button btnEdit = findViewById(R.id.btn_edit);

        ArrayAdapter<String> adapter =  new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, paysGrid);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectPos = position;
                Toast.makeText(getApplicationContext(), ((TextView) view).getText() , Toast.LENGTH_SHORT).show();
            } });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectPos == -1) {
                    Toast.makeText(getApplicationContext(), "Sélectionne un pays d'abord", Toast.LENGTH_SHORT).show();
                    return;
                }

                EditText input = new EditText(getApplicationContext());
                input.setText(paysGrid.get(selectPos));

                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("Modifier")
                        .setView(input)
                        .setPositiveButton("OK", (d, which) -> {
                            String txt = input.getText().toString().trim();
                            if (!txt.isEmpty()) {
                                paysGrid.set(selectPos, txt);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Annuler", null)
                        .show();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = new EditText(getApplicationContext());
                input.setHint("Nouveau pays");

                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("Ajouter")
                        .setView(input)
                        .setPositiveButton("OK", (d, which) -> {
                            String txt = input.getText().toString().trim();
                            if (!txt.isEmpty()) {
                                paysGrid.add(txt);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Annuler", null)
                        .show();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectPos == -1) {
                    Toast.makeText(getApplicationContext(), "Sélectionne un pays d'abord", Toast.LENGTH_SHORT).show();
                    return;
                }

                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("Supprimer")
                        .setMessage("Supprimer : " + paysGrid.get(selectPos) + " ?")
                        .setPositiveButton("Oui", (d, which) -> {
                            paysGrid.remove(selectPos);
                            selectPos = -1;
                            adapter.notifyDataSetChanged();
                        })
                        .setNegativeButton("Non", null)
                        .show();
            }
        });

        btn_retour3 = findViewById(R.id.btn_retour3);
        btn_retour3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iMain = new Intent(Activty_Grid.this , Activty_ListGridSpinner.class);
                startActivity(iMain);
                finish();
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showAddDialog() {

    }

    private void showEditDialog() {

    }

    private void showDeleteDialog() {

    }
}