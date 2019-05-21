package com.example.kit305assignment2journalapp;

import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String EMOTION_KEY = "EMOTION";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main__page);
        Button trackButton = findViewById(R.id.trackButton);
        final Button happyButton = findViewById(R.id.happyButton);
        Button angryButton = findViewById(R.id.angryButton);
        Button sadbutton = findViewById(R.id.sadButton);
        Button neutralButton =findViewById(R.id.neutralButton);

        Database databaseConnection = new Database(this);

        final SQLiteDatabase db = databaseConnection.open();


        JournalEntry journalEntry1 = new JournalEntry();
        journalEntry1.setJournalContents("I feel angery reacts only today");
        journalEntry1.setJournalTitle("Mad!");



        JournalEntry journalEntry2 = new JournalEntry();
        journalEntry2.setJournalContents("I feel sad boiz today!");
        journalEntry2.setJournalTitle("Sad!");



        JournalTable.insert(db, journalEntry1);
        JournalTable.insert(db, journalEntry2);
        final ArrayList<JournalEntry> journals = JournalTable.selectAll(db);
        Log.d("Hi", "com.example.kit305assignment2journalapp.Database Created");

       for (int i=0; i<journals.size(); i++){

            JournalEntry journal = journals.get(i);
            Log.d("TAG", journal.getJournalTitle());
        }



        trackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, Mood_Tracking.class);

                startActivity(i);
            }
        });

        happyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button txtEmotion = findViewById(R.id.happyButton);
                String enteredText = txtEmotion.getText().toString();
                Intent i = new Intent(MainActivity.this, Journal_Entry.class);
                i.putExtra(EMOTION_KEY, enteredText);
                startActivity(i);
            }
        });

        angryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button txtEmotion = findViewById(R.id.angryButton);
                String enteredText = txtEmotion.getText().toString();
                Intent i = new Intent(MainActivity.this, Journal_Entry.class);
                i.putExtra(EMOTION_KEY, enteredText);
                startActivity(i);
            }
        });

        sadbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button txtEmotion = findViewById(R.id.sadButton);
                String enteredText = txtEmotion.getText().toString();
                Intent i = new Intent(MainActivity.this, Journal_Entry.class);
                i.putExtra(EMOTION_KEY, enteredText);
                startActivity(i);
            }
        });

        neutralButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Button txtEmotion = findViewById(R.id.neutralButton);
                String enteredText = txtEmotion.getText().toString();
                Intent i = new Intent(MainActivity.this, Journal_Entry.class);
                i.putExtra(EMOTION_KEY, enteredText);
                startActivity(i);
            }
        });

    }
}

