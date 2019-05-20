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
        Button specificEmotion = findViewById(R.id.specificEmotionButton);
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

        specificEmotion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(Journal_Entry.this, PopUpActivity.class);
                startActivity(i);
            }
        });

    }

    public static void Emotions(String emotion) {
        ArrayList<String> emotions = new ArrayList<String>();
        switch (emotion){
            case "Happy":
                    emotions.add("Cheerful");
                    emotions.add("Contented");
                    emotions.add("Delighted");
                    emotions.add("Ecstatic");
                    emotions.add("Elated");
                    emotions.add("Glad");
                    emotions.add("Joyful");
                    emotions.add("Joyous");
                    emotions.add("Jubilant");
                    emotions.add("Lively");
                    emotions.add("Merry");
                    emotions.add("Overjoyed");
                    emotions.add("Peaceful");
                    emotions.add("Pleasant");
                    emotions.add("Thrilled");
                    emotions.add("Upbeat");
                break;
            case "Sad":
                    emotions.add("Bitter");
                    emotions.add("Dismal");
                    emotions.add("Heartbroken");
                    emotions.add("Melancholy");
                    emotions.add("Mournful");
                    emotions.add("Pessimistic");
                    emotions.add("Somber");
                    emotions.add("Sorrowful");
                    emotions.add("Sorry");
                    emotions.add("Wistful");
                    emotions.add("Bereaved");
                    emotions.add("Blue");
                break;
            case "Angry":
                    emotions.add("annoyed");
                    emotions.add("Bitter");
                    emotions.add("Enraged");
                    emotions.add("Exasperated");
                    emotions.add("Furious");
                    emotions.add("Heated");
                    emotions.add("Impassioned");
                    emotions.add("Indignant");
                    emotions.add("Irate");
                    emotions.add("Irritable");
                    emotions.add("Irritated");
                    emotions.add("Offended");
                    emotions.add("Outraged");
                    emotions.add("Resentful");
                    emotions.add("Sullen");
                    emotions.add("Uptight");
                break;
            case "Neutral":
                    emotions.add("Disinterested");
                    emotions.add("Evenhanded");
                    emotions.add("Fair-minded");
                    emotions.add("Inactive");
                    emotions.add("Indifferent");
                    emotions.add("Nonaligned");
                    emotions.add("Nonpartisan");
                    emotions.add("Unbiased");
                    emotions.add("Uncommitted");
                    emotions.add("Undecided");
                    emotions.add("Uninvolved");
                break;
            default:
        }
    }
}
