package com.example.g.quizzz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Summary extends AppCompatActivity {
    private int corQ;
    private int chq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        // Loads saved settings from previous Activities
        Bundle extras = getIntent().getExtras();
        chq = extras.getInt("max");
        Log.d("SumA", "Max questions: " + chq);
        corQ = extras.getInt("corQ");
        Log.d("SumA", "Correct questions: " + corQ);
        //Allocates GUI
        TextView scoreg = (TextView) findViewById(R.id.scoreg);
        TextView scorem = (TextView) findViewById(R.id.scorem);

        if (corQ < 10) {
            scoreg.setText("0" + corQ);
        } else {
            scoreg.setText("" + corQ);
        }
        if ((chq +1)< 10) {
            scorem.setText("0" + (chq + 1));
        } else {
            scorem.setText("" + (chq + 1));
        }
        Toast toast = Toast.makeText(getApplicationContext(), "Answers you got correct: " + corQ + "!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void Redo(View view) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
    }
}