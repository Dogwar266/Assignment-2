package com.example.kit305assignment2journalapp;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Journal_Entry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal__entry);
        ImageButton openCamera = findViewById(R.id.cameraButton);
        final ImageButton openGallery = findViewById(R.id.galleryButton);
        Button saveButton = findViewById(R.id.saveButton);
        TextView emotionLabel = findViewById(R.id.emotionLabel);
        Bundle extras = getIntent().getExtras();
        emotionLabel.setText(extras.getString(MainActivity.EMOTION_KEY));



        openCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(i);

            }
        });


        openGallery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Intent.ACTION_VIEW, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.setType("image/*");
                Intent.createChooser(i.setType("image/*"), "Image");
                startActivity(i);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Journal_Entry.this, Mood_Tracking.class);
                startActivity(i);
            }
        });

    }
}
