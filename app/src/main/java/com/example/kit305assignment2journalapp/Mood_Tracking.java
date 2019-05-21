package com.example.kit305assignment2journalapp;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class Mood_Tracking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood__tracking);
        ListView journalList = findViewById(R.id.journalList);
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();
        final ArrayList<JournalEntry> journals = JournalTable.selectAll(db);


        JournalAdapter journalListAdapter =
                new JournalAdapter(getApplicationContext(),
                        R.layout.my_journal_list, journals);

        journalList.setAdapter(journalListAdapter);

    }
}
