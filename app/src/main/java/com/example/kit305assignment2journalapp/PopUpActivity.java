package com.example.kit305assignment2journalapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class PopUpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        final Bundle extras = getIntent().getExtras();
        String emotion = extras.getString(Journal_Entry.EMOTION_KEY);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .6), (int) (height * .6));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);


        final ArrayList<String> emotions = new ArrayList<String>();
        switch (emotion) {
            case "HAPPY!":
                emotions.add("Cheerful");
                emotions.add("Contented");
                emotions.add("Delighted");
                emotions.add("Ecstatic");
                emotions.add("Elated");
                emotions.add("Glad");
                emotions.add("Joyful");
                emotions.add("Joyous");
                emotions.add("Jubilant");
                emotions.add("Lively");
                emotions.add("Merry");
                emotions.add("Overjoyed");
                emotions.add("Peaceful");
                emotions.add("Pleasant");
                emotions.add("Thrilled");
                emotions.add("Upbeat");
                break;
            case "SAD":
                emotions.add("Bitter");
                emotions.add("Dismal");
                emotions.add("Heartbroken");
                emotions.add("Melancholy");
                emotions.add("Mournful");
                emotions.add("Pessimistic");
                emotions.add("Somber");
                emotions.add("Sorrowful");
                emotions.add("Sorry");
                emotions.add("Wistful");
                emotions.add("Bereaved");
                emotions.add("Blue");
                break;
            case "ANGRY":
                emotions.add("Annoyed");
                emotions.add("Bitter");
                emotions.add("Enraged");
                emotions.add("Exasperated");
                emotions.add("Furious");
                emotions.add("Heated");
                emotions.add("Impassioned");
                emotions.add("Indignant");
                emotions.add("Irate");
                emotions.add("Irritable");
                emotions.add("Irritated");
                emotions.add("Offended");
                emotions.add("Outraged");
                emotions.add("Resentful");
                emotions.add("Sullen");
                emotions.add("Uptight");
                break;
            case "Neutral":
                emotions.add("Disinterested");
                emotions.add("Evenhanded");
                emotions.add("Fair-minded");
                emotions.add("Inactive");
                emotions.add("Indifferent");
                emotions.add("Nonaligned");
                emotions.add("Nonpartisan");
                emotions.add("Unbiased");
                emotions.add("Uncommitted");
                emotions.add("Undecided");
                emotions.add("Uninvolved");
                break;
            default:
                break;
        }




        ArrayAdapter<String> emotionAdapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                emotions);

        ListView specificEmotions = findViewById(R.id.emotionList);
        specificEmotions.setAdapter(emotionAdapter);

        /*specificEmotions.setOnClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i){
                AlertDialog.Builder builder = new AlertDialog.Builder(PopUpActivity.this);
                builder.setMessage(emotions.get(i)).setTitle("Item Tapped");
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }); */
    }
}
