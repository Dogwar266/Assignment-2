package com.example.kit305assignment2journalapp;

import android.provider.MediaStore;

import java.util.Date;

public class JournalEntry {
    private String mJournalTitle;
    private String mJournalContents;
    private int mJournalID;
    private String mJournalDate;

    public String getJournalTitle() { return mJournalTitle;  }
    public void setJournalTitle(String s) { this.mJournalTitle = s; }

    public String getJournalContents() { return mJournalContents; }
    public void setJournalContents(String s) {  this.mJournalContents = s; }

    public int getmJournalID() { return mJournalID; }
    public void setmJournalID (int id) { this.mJournalID = id; }

    public String getJournalDate() { return mJournalDate; }
    public void setJournalDate(String s) {  this.mJournalDate = s; }

}
