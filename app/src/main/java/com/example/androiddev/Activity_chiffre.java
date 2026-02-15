package com.example.androiddev;

import android.content.Intent;
import android.media.MediaPlayer;
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

public class Activity_chiffre extends AppCompatActivity {
    ImageView img;
    MediaPlayer currentAudio = new MediaPlayer();
    Button retour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chiffre);

        retour=findViewById(R.id.btn_retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_main1 = new Intent(Activity_chiffre.this , Main1.class);
                startActivity(i_main1);
                finish();
            }
        });

        for (int i = 0; i <= 9; i++) {

            final int index = i;
            int resID = getApplicationContext().getResources().getIdentifier("img" + i, "id", getPackageName());
            img = findViewById(resID);


            img.setTag(i);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(currentAudio.isPlaying())currentAudio.stop();
                    String uriPath = "android.resource://" + getPackageName() + "/raw/a" + index;
                    Uri audioUri = Uri.parse(uriPath);
                    currentAudio = MediaPlayer.create(getApplicationContext(),audioUri);
                    currentAudio.start();
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