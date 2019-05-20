package com.example.kit305assignment2journalapp;

import android.provider.MediaStore;

public class JournalEntry {
    private String mJournalTitle;
    private String mJournalContents;

    public String getJournalTitle() { return mJournalTitle;  }
    public void setJournalTitle(String s) { this.mJournalTitle = s; }

    public String getJournalContents() { return mJournalContents; }
    public void setJournalContents(String s) {  this.mJournalContents = s; }

}
