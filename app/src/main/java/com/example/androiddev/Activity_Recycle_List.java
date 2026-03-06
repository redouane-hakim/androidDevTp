package com.example.androiddev;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class Activity_Recycle_List extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btnAdd, btnEdit, btnDelete;

    ArrayList<PaysItem> paysList  = new ArrayList<>();
    PaysAdapter adapter;


    private final int DEFAULT_IMG = android.R.drawable.ic_menu_gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_list);

        recyclerView = findViewById(R.id.recycler_pays);
        btnAdd = findViewById(R.id.btn_add);
        btnEdit = findViewById(R.id.btn_edit);
        btnDelete = findViewById(R.id.btn_delete);

        PaysItem[] paysArray = new PaysItem[] {
                new PaysItem("France", R.drawable.france),
                new PaysItem("Italie", R.drawable.italy),
                new PaysItem("Allemagne", R.drawable.germany),
                new PaysItem("Espagne", R.drawable.espagne),
                new PaysItem("Suède", R.drawable.swede),
                new PaysItem("Maroc", R.drawable.morroco),
                new PaysItem("Égypte", R.drawable.egypth),
                new PaysItem("Japon", R.drawable.japon),
                new PaysItem("Indie", R.drawable.indie)
        };

        paysList.addAll(Arrays.asList(paysArray));
        adapter = new PaysAdapter(paysList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnAdd.setOnClickListener(v -> showAddDialog());
        btnEdit.setOnClickListener(v -> showEditDialog());
        btnDelete.setOnClickListener(v -> showDeleteDialog());

    }

    private void showAddDialog() {
        EditText input = new EditText(this);
        input.setHint("Title");
        input.setInputType(InputType.TYPE_CLASS_TEXT);

        new AlertDialog.Builder(this)
                .setTitle("Ajouter")
                .setView(input)
                .setPositiveButton("OK", (d, which) -> {
                    String title = input.getText().toString().trim();
                    if (title.isEmpty()) {
                        Toast.makeText(this, "Champ vide", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    adapter.addItem(new PaysItem(title, DEFAULT_IMG));
                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    private void showEditDialog() {
        int pos = adapter.getSelectedPos();
        if (pos == -1) {
            Toast.makeText(this, "Sélectionne un item d'abord", Toast.LENGTH_SHORT).show();
            return;
        }

        EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setText(adapter.getItem(pos).getTitle());

        new AlertDialog.Builder(this)
                .setTitle("Modifier")
                .setView(input)
                .setPositiveButton("OK", (d, which) -> {
                    String newTitle = input.getText().toString().trim();
                    if (newTitle.isEmpty()) {
                        Toast.makeText(this, "Champ vide", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    adapter.updateTitle(pos, newTitle);
                })
                .setNegativeButton("Annuler", null)
                .show();
    }

    private void showDeleteDialog() {
        int pos = adapter.getSelectedPos();
        if (pos == -1) {
            Toast.makeText(this, "Sélectionne un item d'abord", Toast.LENGTH_SHORT).show();
            return;
        }

        String title = adapter.getItem(pos).getTitle();

        new AlertDialog.Builder(this)
                .setTitle("Supprimer")
                .setMessage("Supprimer : " + title + " ?")
                .setPositiveButton("Oui", (d, which) -> adapter.removeItem(pos))
                .setNegativeButton("Non", null)
                .show();
    }
}