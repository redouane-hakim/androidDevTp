package com.example.androiddev;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity_media extends AppCompatActivity {

    TextView animaleName;
    Button btn_retour;
    VideoView vidAnimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_media);

        animaleName = findViewById(R.id.txt_name);
        animaleName.setText(getIntent().getStringExtra("animalName"));
        animaleName.setTextSize(50);
        vidAnimal = findViewById(R.id.vid_animal);

        try{
            Uri videoUri = getIntent().getParcelableExtra("videoUri" , Uri.class);
            vidAnimal.setVideoURI(videoUri);

            MediaController mcvid = new MediaController(getApplicationContext());
            vidAnimal.setMediaController(mcvid);
            mcvid.setAnchorView(vidAnimal);

            vidAnimal.start();
        }
        catch (Exception e){
            Log.e("MY_APP", "Error loading video: " + e.getMessage());
            Toast.makeText(this, "Video could not be loaded", Toast.LENGTH_SHORT).show();
        }


        btn_retour = findViewById(R.id.btn_retour);
        btn_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iMain = new Intent(Activity_media.this , Activity_Zoo.class);
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
}