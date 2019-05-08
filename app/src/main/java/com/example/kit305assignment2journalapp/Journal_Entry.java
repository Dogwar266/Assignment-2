package com.example.kit305assignment2journalapp;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Journal_Entry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal__entry);
        ImageButton openCamera = findViewById(R.id.cameraButton);
        final ImageButton openGallery = findViewById(R.id.galleryButton);



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
    }
}
