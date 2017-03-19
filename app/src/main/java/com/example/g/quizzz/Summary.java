package com.example.g.quizzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Summary extends AppCompatActivity {
    private int corQ;
    private int chq;
    private float max;
    private float corr;
    private String[] questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        // Loads saved settings from previous Activities
        Bundle extras = getIntent().getExtras();
        chq = extras.getInt("max");
        Log.d("SumA", "Max questions: " + chq);
        corQ = extras.getInt("corQ");
        max = (float) chq;
        corr = (float) corQ;
        Log.d("SumA", "Correct questions: " + corQ);
        questions = new String[chq];
        //Allocates GUI
        TextView scoreg = (TextView) findViewById(R.id.scoreg);
        TextView scorem = (TextView) findViewById(R.id.scorem);

        if (corQ < 10) {
            scoreg.setText("0" + corQ);
        } else {
            scoreg.setText(corQ);
        }
        if (chq < 10) {
            scorem.setText("0" + chq);
        } else {
            scorem.setText(chq);
        }
    }
    public void Redo (View view) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }
}