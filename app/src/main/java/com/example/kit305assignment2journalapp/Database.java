package com.example.kit305assignment2journalapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.kit305assignment2journalapp.Journal_Entry;

import java.util.ArrayList;

public class Database {

    private static final String DATABASE_NAME = "JournalEntries";
    private SQLiteDatabase mDb;
    private DatabaseHelper mDbHelper;
    private Context mCtx;

    private static final int DATABASE_VERSION = 9;

    public Database(Context ctx) {this.mCtx = ctx;}

    public SQLiteDatabase open() {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return mDb;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db){

            db.execSQL(JournalTable.CREATE_STATEMENT);

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

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            Log.d("TAG", "DatabaseHelper onUpgrade");
            db.execSQL("DROP TABLE IF EXISTS " + JournalTable.TABLE_NAME);
            onCreate(db);
            Log.d("TAG", " I got here, yay!");
        }

    }
}
