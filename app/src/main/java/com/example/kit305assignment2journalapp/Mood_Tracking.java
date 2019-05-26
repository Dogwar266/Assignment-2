package com.example.kit305assignment2journalapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;



public class Mood_Tracking extends AppCompatActivity {
    public static String EMOTION_KEY = "EMOTION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood__tracking);
        final ListView journalList = findViewById(R.id.journalList);
        ImageButton homeButton = findViewById(R.id.homeButton);
        final Button weekButton = findViewById(R.id.weekButton);
        Button monthButton = findViewById(R.id.monthButton);
        Button yearButton = findViewById(R.id.yearButton);
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();
        final ArrayList<JournalEntry> journals = JournalTable.selectAll(db);


        final GraphView graph = (GraphView) findViewById(R.id.graph);

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });

        graph.addSeries(series);

        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });

        series.setSpacing(50);
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);

        graph.getGridLabelRenderer().setNumHorizontalLabels(4);

        final StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        staticLabelsFormatter.setHorizontalLabels(new String[]{ "Happy", "Sad", "Angry", "Neutral"});

        weekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graph.getGridLabelRenderer().setNumVerticalLabels(7);
                staticLabelsFormatter.setVerticalLabels(new String[]{ "1", "2", "3", "4", "5", "6", "7"});

                graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            }
        });

        monthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graph.getGridLabelRenderer().setNumVerticalLabels(4);
                staticLabelsFormatter.setVerticalLabels(new String[]{ "1", "2", "3", "4"});

                graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            }
        });

        yearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graph.getGridLabelRenderer().setNumVerticalLabels(12);
                staticLabelsFormatter.setVerticalLabels(new String[]{ "1", "2", "3", "4", "5", "6", "6", "7", "8", "9", "10", "11", "12"});

                graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
            }
        });



        staticLabelsFormatter.setVerticalLabels(new String[]{ "1", "2", "3", "4", "5", "6", "7"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        graph.setTitle("Mood Over Time");




        final JournalAdapter journalListAdapter =
                new JournalAdapter(getApplicationContext(),
                        R.layout.my_journal_list, journals);

        journalList.setAdapter(journalListAdapter);




        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Mood_Tracking.this, MainActivity.class);
                startActivity(i);
            }
        });

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

               builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
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


       journalList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               final JournalEntry j = journals.get(position);

               Intent i = new Intent(Mood_Tracking.this, journalDetails.class);

               String journalTitle = j.getJournalTitle();
               String journalContents = j.getJournalContents();
               String journalDate = j.getJournalDate();


               i.putExtra("journalTitle", journalTitle);

               i.putExtra("journalContents", journalContents);

               i.putExtra("journalDate", journalDate);

               startActivity(i);

               return true;
           }
       });


    }
}
