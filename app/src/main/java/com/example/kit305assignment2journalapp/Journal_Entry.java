package com.example.kit305assignment2journalapp;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Journal_Entry extends AppCompatActivity {

    public static String EMOTION_KEY = "EMOTION";
    ImageButton openGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal__entry);
        Button specificEmotion = findViewById(R.id.specificEmotionButton);
        ImageButton openCamera = findViewById(R.id.cameraButton);
        Button addAttachment = findViewById(R.id.addAttachement);
        Button shareButton = findViewById(R.id.shareButton);
        openGallery = findViewById(R.id.galleryButton);
        Button saveButton = findViewById(R.id.saveButton);
        ImageButton homeButton = findViewById(R.id.homeButton);
        final Date date = Calendar.getInstance().getTime();
        final String currentTime = date.toString();
        final TextView emotionLabel = findViewById(R.id.emotionLabel);
        final TextView journalContents = findViewById(R.id.editText);
        final Bundle extras = getIntent().getExtras();
        emotionLabel.setText(extras.getString(MainActivity.EMOTION_KEY));
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();



        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Journal_Entry.this, MainActivity.class);
                startActivity(i);
            }
        });

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
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                i.setType("image/*");
                Intent.createChooser(i.setType("image/*"), "Image");
                startActivity(i);
            }
        });

        addAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("*/*");
                startActivityForResult(i, 500);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                JournalEntry journalEntry1 = new JournalEntry();
                journalEntry1.setJournalTitle(emotionLabel.getText().toString());
                journalEntry1.setJournalContents(journalContents.getText().toString());
                journalEntry1.setJournalDate(currentTime);
                JournalTable.insert(db, journalEntry1);
                Intent i = new Intent(Journal_Entry.this, Mood_Tracking.class);
                startActivity(i);
            }
        });

        specificEmotion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView txtEmotion = findViewById(R.id.emotionLabel);
                String enteredText = txtEmotion.getText().toString();
                Intent i = new Intent(Journal_Entry.this, PopUpActivity.class);
                i.putExtra(EMOTION_KEY, enteredText);
                startActivity(i);
            }
        });

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setAction(Intent.ACTION_SEND);
               i.putExtra(Intent.EXTRA_TEXT, emotionLabel.getText().toString());
                i.setType("text/plain");
                startActivity(i);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 500:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = (getContentResolver().openInputStream(imageUri));
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        openGallery.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }

}
