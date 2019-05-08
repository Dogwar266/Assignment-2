package com.example.kit305assignment2journalapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class sqliteDatabase {



    public static class FeedEntry implements BaseColumns{
        public static final String TABLE_NAME = "jounalEntries";
        public static final String COLUMN_NAME_TITLE = "title";

    }

    public class FeedReaderDbHelper extends SQLiteOpenHelper {
        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + FeedEntry.TABLE_NAME + " ( " + FeedEntry._ID  + " INTEGER PRIMARY KEY, " + FeedEntry.COLUMN_NAME_TITLE + " TEXT)";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "JournalEntries.db";

        public FeedReaderDbHelper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate(SQLiteDatabase db){
            db.execSQL(SQL_CREATE_ENTRIES);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL(SQL_DELETE_ENTRIES);
            onCreate(db);
        }
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }

    }
}
