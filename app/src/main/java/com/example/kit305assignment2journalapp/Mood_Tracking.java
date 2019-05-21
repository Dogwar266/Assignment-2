package com.example.kit305assignment2journalapp;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class Mood_Tracking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood__tracking);
        final ListView journalList = findViewById(R.id.journalList);
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();
        final ArrayList<JournalEntry> journals = JournalTable.selectAll(db);


        final JournalAdapter journalListAdapter =
                new JournalAdapter(getApplicationContext(),
                        R.layout.my_journal_list, journals);

        journalList.setAdapter(journalListAdapter);

       journalList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               final JournalEntry j = journals.get(position);
               AlertDialog.Builder builder = new AlertDialog.Builder(Mood_Tracking.this);
               builder.setTitle("Update your Journal Entry");

               final EditText input = new EditText(Mood_Tracking.this);
               input.setInputType(InputType.TYPE_CLASS_TEXT);
               input.setText(j.getJournalContents());
               builder.setView(input);

               builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                   @Override
               public void onClick(DialogInterface dialog, int which){
                       dialog.cancel();
                   }
               });

               builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       j.setJournalContents(input.getText().toString());

                       JournalTable.update(db, j);
                       journalListAdapter.notifyDataSetChanged();
                   }
               });

               builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       JournalTable.delete(db, j);
                       journalListAdapter.notifyDataSetChanged();
                   }
               });

               builder.create().show();
           }
       });



    }
}
