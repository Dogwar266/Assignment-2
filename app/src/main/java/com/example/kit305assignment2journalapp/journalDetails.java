package com.example.kit305assignment2journalapp;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class journalDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_details);
        final Bundle extras = getIntent().getExtras();
        TextView titleLabel = findViewById(R.id.lblTitle);
        TextView journalContents = findViewById(R.id.lblContents);
        TextView dateWritten = findViewById(R.id.dateWritten);
        TextView timeWritten = findViewById(R.id.timeWritten);
        TextView timesFelt = findViewById(R.id.timesFelt);
        Database databaseConnection = new Database(this);

        final SQLiteDatabase db = databaseConnection.open();

        final ArrayList<JournalEntry> journals = JournalTable.selectAll(db);


        Log.d("ExtrasAfterIntent", extras.getString("journalTitle"));
        titleLabel.setText(extras.getString("journalTitle"));
        journalContents.setText(extras.getString("journalContents"));
        dateWritten.setText(extras.getString("journalDate"));






    }
}
