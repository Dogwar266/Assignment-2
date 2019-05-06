package com.example.kit305assignment2journalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main__page);

        Button trackButton = findViewById(R.id.trackButton);
        Button happyButton = findViewById(R.id.happyButton);
        Button angryButton = findViewById(R.id.angryButton);
        Button sadbutton = findViewById(R.id.sadButton);
        Button nuetralButton =findViewById(R.id.nuetralButton);

        trackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Mood_Tracking.class);
                startActivity(i);
            }
        });




    }
}
