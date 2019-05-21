package com.example.kit305assignment2journalapp;

import android.provider.MediaStore;

public class JournalEntry {
    private String mJournalTitle;
    private String mJournalContents;
    private int mJournalID;

    public String getJournalTitle() { return mJournalTitle;  }
    public void setJournalTitle(String s) { this.mJournalTitle = s; }

    public String getJournalContents() { return mJournalContents; }
    public void setJournalContents(String s) {  this.mJournalContents = s; }

    public int getmJournalID() { return mJournalID; }
    public void setmJournalTitle(int id) { this.mJournalID = id; }

}
