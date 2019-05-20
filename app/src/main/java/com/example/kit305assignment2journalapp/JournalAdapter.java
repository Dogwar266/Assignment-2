package com.example.kit305assignment2journalapp;

import android.app.Service;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class JournalAdapter extends ArrayAdapter<JournalEntry> {

    private int mLayoutResourceID;

    public JournalAdapter(Context context, int resource, List<JournalEntry> objects){
        super(context, resource, objects);
        this.mLayoutResourceID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater =
                (LayoutInflater)getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(mLayoutResourceID, parent, false);
        JournalEntry j = this.getItem(position);
        TextView textView = row.findViewById(R.id.journalList);
        textView.setText(j.getJournalTitle()+": "+j.getJournalContents());

        return row;
    }
}
