package com.example.androiddev;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class Activity_List extends AppCompatActivity {

    ListView listView;
    Button btnAdd, btnEdit, btnDelete;

    ArrayList<String> paysList;
    ArrayAdapter<String> adapter;

    int selectedPos = -1; // last clicked item

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.list);
        btnAdd = findViewById(R.id.btn_add);
        btnEdit = findViewById(R.id.btn_edit);
        btnDelete = findViewById(R.id.btn_delete);

        String[] paysArray = {
                "France", "Italie", "Allemagne", "Espagne", "Suède",
                "Maroc", "Sénégal", "Égypte", "Afrique du Sud", "Japon",
                "Inde", "Thaïlande"
        };

        paysList = new ArrayList<>(Arrays.asList(paysArray));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, paysList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            selectedPos = position;
            Toast.makeText(this, paysList.get(position), Toast.LENGTH_SHORT).show();
        });

        btnAdd.setOnClickListener(v -> showAddDialog());
        btnEdit.setOnClickListener(v -> showEditDialog());
        btnDelete.setOnClickListener(v -> showDeleteDialog());


        Button btn_retour2 = findViewById(R.id.btn_retour2);
        btn_retour2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iMain = new Intent(Activity_List.this , Activty_ListGridSpinner.class);
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
        EditText input = new EditText(this);
        input.setHint("Nouveau pays");

        new AlertDialog.Builder(this)
                .setTitle("Ajouter")
                .setView(input)
                .setPositiveButton("OK", (d, which) -> {
                    String txt = input.getText().toString().trim();
                    if (!txt.isEmpty()) {
                        paysList.add(txt);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    private void showEditDialog() {
        if (selectedPos == -1) {
            Toast.makeText(this, "Sélectionne un pays d'abord", Toast.LENGTH_SHORT).show();
            return;
        }

        EditText input = new EditText(this);
        input.setText(paysList.get(selectedPos));

        new AlertDialog.Builder(this)
                .setTitle("Modifier")
                .setView(input)
                .setPositiveButton("OK", (d, which) -> {
                    String txt = input.getText().toString().trim();
                    if (!txt.isEmpty()) {
                        paysList.set(selectedPos, txt);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    private void showDeleteDialog() {
        if (selectedPos == -1) {
            Toast.makeText(this, "Sélectionne un pays d'abord", Toast.LENGTH_SHORT).show();
            return;
        }

        new AlertDialog.Builder(this)
                .setTitle("Supprimer")
                .setMessage("Supprimer : " + paysList.get(selectedPos) + " ?")
                .setPositiveButton("Oui", (d, which) -> {
                    paysList.remove(selectedPos);
                    selectedPos = -1;
                    adapter.notifyDataSetChanged();
                })
                .setNegativeButton("Non", null)
                .show();
    }


}