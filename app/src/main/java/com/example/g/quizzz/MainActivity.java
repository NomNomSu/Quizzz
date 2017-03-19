package com.example.g.quizzz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    private int maxQ = 12;
    private int currQ;
    private String[] questions = new String[12];
    private String[] chosen_questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // gui allocation
        final Button incQ = (Button) findViewById(R.id.inc_questions);
        final Button decQ = (Button) findViewById(R.id.dec_questions);
        Button start_button = (Button) findViewById(R.id.start);

        // button listeners with logic
        incQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currQ < maxQ - 1) {
                    incQ.setEnabled(true);
                    decQ.setEnabled(true);
                } else {incQ.setEnabled(false);}
                currQ = currQ + 1;
                displayWhenClicked(currQ);
                //Log.d("Iteration", "User wants to answer:" + currQ + "questions.");
            }
        });
        decQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currQ == 1) {
                    decQ.setEnabled(false);
                    incQ.setEnabled(true);
                } else {decQ.setEnabled(true);}
                currQ = currQ - 1;
                displayWhenClicked(currQ);
                //Log.d("Decimation", "User wants to answer:" + currQ + "questions.");
            }
        });
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateArray();
                startQuiz();
            }
        });
    }
    //displays number of questions when value changed
    private void displayWhenClicked(int currQ) {
        TextView tmq = (TextView) findViewById(R.id.question_counter);
        if (currQ < 10) {
            tmq.setText( "0" + currQ);
        } else {tmq.setText( "" + currQ);}
    }

    private void populateArray() {
        questions[0] = "single;What is the name of paranoid android in the Hitchhiker's Guide to the Galaxy?;Flippo;Marvin;Bob;Misery;Marvin";
        questions[1] = "multi;Which three countries have both an Atlantic and Mediterranean coastline?;France;Spain;Italy;Morocco;true;true;false;true";
        questions[2] = "single;Who do the Italians call Topolino? ;God Father;Mickey Mouse;Tourists;Children;Mickey Mouse";
        questions[3] = "multi;Which were the original pieces in a Monopoly game?;Ship;Ball;Dog;Phone;true;false;true;false";
        questions[4] = "single;Which part of the body is most sensitive to radiation?;Brain;Skin;Blood;Lungs;Blood";
        questions[5] = "multi;On the flags of which two countries would you find the most stars ?;Cuba;USA;Brazil;Venezuela;false;true;true;false";
        questions[6] = "single;Which number in Morse code is represented by five dots?;4;5;8;50;5";
        questions[7] = "multi;The RMS Titanic had two sister ships. What were their names?;Adelaide;Victory;Brittanic;Olympic;flase;false;true;true";
        questions[8] = "single;What is the official language or languages of the United States?;English;German;French;None;None";
        questions[9] = "multi;The Oslo Accords of 1993 were a step forward in the peace process between which two parties?;Palestine;Syria;Iraq;Israel;true;false;false;true";
        questions[10] = "single;Who called himself 8th wonder of world cos of his big dick?;Hitler;Charlie Chaplain;Trumph;Arnold Schwarzenegger;Charlie Chaplain";
        questions[11] = "multi;Where was Barack Obama born?;Mexico;Honolulu;USA;Hawaii;false;true;false;true";
        //Log.d("Array","question 1 is:" + questions[0] + " and question 2 is:" + questions[1]);
    }

    private void startQuiz () {
        randomizeQuestions(currQ);
        //startActivity(new Intent(MainActivity.this, Questions.class));
        Intent intent = new Intent(getBaseContext(), Questions.class);
        intent.putExtra("questions", chosen_questions);
        intent.putExtra("currQ", currQ);
        startActivity(intent);
    }

    private void randomizeQuestions (int hmq) {
        chosen_questions = new String [11];
        List<Integer> chosenQ = new ArrayList();
        boolean repeats;
        // randomly takes 1 question and makes sure that they do not repeat
        for (int i = 0; i <= hmq; i++) {
            do {
                int random_number = randInt(hmq);
                if (chosenQ.contains(random_number)) {
                    repeats = true;
                    //Log.d("Randomizer","Question " + random_number + " is already on list");
                } else {chosenQ.add(random_number); chosen_questions[i] = questions[random_number]; repeats = false; Log.d("Randomizer","Added question" + random_number +" to list of questions");}
            } while (repeats);
        }
        //Log.d("Randomizer", chosen_questions[0]);
        //Log.d("Randomizer", chosen_questions[1]);
        //Log.d("Randomizer", chosen_questions[2]);
        //Log.d("Randomizer", chosen_questions[3]);
    }

    public static int randInt(int max) {
        int randomNum = new Random().nextInt(max + 1);
        Log.d("Randomizer", "Random numer is: " + randomNum);
        return randomNum;
    }
}
