package com.example.androiddev;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_Zoo extends AppCompatActivity {
String[] animaux = {"dog","cat","mouton","rhino","tiger","eleph"};

Button btn_retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_zoo);
        btn_retour = findViewById(R.id.btn_retour);
        btn_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iMain = new Intent(Activity_Zoo.this , Main1.class);
                startActivity(iMain);
                finish();
            }
        });

        for(String animal:animaux){
            final String animalChoix = animal;

            int resID = getApplicationContext().getResources().getIdentifier("img_" + animal, "id", getPackageName());
            ImageView img = findViewById(resID);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent imedia = new Intent( Activity_Zoo.this , Activity_media.class);
                    String uriPath = "android.resource://" + getPackageName() + "/raw/vid_" + animalChoix;
                    Uri videoUri = Uri.parse(uriPath);
                    imedia.putExtra("videoUri",videoUri);
                    imedia.putExtra("animalName", animalChoix);
                    startActivity(imedia);
                    finish();
                }
            });
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}