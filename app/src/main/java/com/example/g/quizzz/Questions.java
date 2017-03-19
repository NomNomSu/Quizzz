package com.example.g.quizzz;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Questions extends AppCompatActivity {
    private String[] questions;
    private int curQ = 0;
    private int chq;
    private CountDownTimer cDown;
    private int corQ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //added
        Bundle extras = getIntent().getExtras();
        chq = extras.getInt("currQ");
        questions = new String[chq];
        questions = extras.getStringArray("questions");
        //Log.d("CurrQ", "" + chq);
        //Log.d("Array", questions[0]);

        //SplitQuestion(questions);
        QuestionEngine();
    }
    public String[] SplitQuestion(String [] questions) {
        String [] split_question = questions[curQ].split(";");
        //Log.d("Splitter", "Current question to split is nr: " + curQ);
        //Log.d("Splitter", "Question is split to: " + split_question[0]);
        //Log.d("Splitter", "Question is split to: " + split_question[1]);
        //Log.d("Splitter", "Question is split to: " + split_question[2]);
        //Log.d("Splitter", "Question is split to: " + split_question[3]);
        //Log.d("Splitter", "Question is split to: " + split_question[4]);
        //Log.d("Splitter", "Question is split to: " + split_question[5]);
        return split_question;
        }
    /*public void CDown() {
        new CountDownTimer(16000, 0001) {

            TextView cD = (TextView) findViewById(R.id.timer);
            ProgressBar pB = (ProgressBar) findViewById(R.id.progress_bar);

            public void onTick(long millisUntilFinished) {
                Integer clock = (int) (long) millisUntilFinished / 1000;
                cD.setText("" + millisUntilFinished / 1000);
                pB.setProgress(clock);
                pB.setMax(16);
            }

            public void onFinish() {
                CheckAnswer();
            }

        }.start();
    }*/
    public void QuestionEngine () {
        String [] split_question = SplitQuestion(questions);
        //Log.d("QEngine", split_question[0]);
        //Log.d("QEngine","" + chq);
        if (split_question[0].equals("single") && curQ < chq) {
            setContentView(R.layout.activity_questions_radio);
            RadioButton answer_one_radio = (RadioButton) findViewById(R.id.answer_one_radio);
            RadioButton answer_two_radio = (RadioButton) findViewById(R.id.answer_two_radio);
            RadioButton answer_three_radio = (RadioButton) findViewById(R.id.answer_three_radio);
            RadioButton answer_four_radio = (RadioButton) findViewById(R.id.answer_four_radio);
            TextView question = (TextView) findViewById(R.id.question);
            question.setText(split_question[1]);
            answer_one_radio.setText(split_question[2]);
            answer_two_radio.setText(split_question[3]);
            answer_three_radio.setText(split_question[4]);
            answer_four_radio.setText(split_question[5]);
            cDown = new CountDownTimer(16000, 0001) {

                TextView cD = (TextView) findViewById(R.id.timer);
                ProgressBar pB = (ProgressBar) findViewById(R.id.progress_bar);

                public void onTick(long millisUntilFinished) {
                    Integer clock = (int) (long) millisUntilFinished / 1000;
                    cD.setText("" + millisUntilFinished / 1000);
                    pB.setProgress(clock);
                    pB.setMax(16);
                }

                public void onFinish() {
                    CheckAnswer();
                }
            };
            cDown.start();
            ButtonHander();

        }
        else if (split_question[0].equals("multi") && curQ < chq) {
            setContentView(R.layout.activity_questions_checkbox);
            CheckBox answer_one_box = (CheckBox) findViewById(R.id.answer_one_box);
            CheckBox answer_two_box = (CheckBox) findViewById(R.id.answer_two_box);
            CheckBox answer_three_box = (CheckBox) findViewById(R.id.answer_three_box);
            CheckBox answer_four_box = (CheckBox) findViewById(R.id.answer_four_box);
            TextView question = (TextView) findViewById(R.id.question);
            question.setText(split_question[1]);
            answer_one_box.setText(split_question[2]);
            answer_two_box.setText(split_question[3]);
            answer_three_box.setText(split_question[4]);
            answer_four_box.setText(split_question[5]);
            cDown = new CountDownTimer(16000, 0001) {

                TextView cD = (TextView) findViewById(R.id.timer);
                ProgressBar pB = (ProgressBar) findViewById(R.id.progress_bar);

                public void onTick(long millisUntilFinished) {
                    Integer clock = (int) (long) millisUntilFinished / 1000;
                    cD.setText("" + millisUntilFinished / 1000);
                    pB.setProgress(clock);
                    pB.setMax(16);
                }

                public void onFinish() {
                    CheckAnswer();
                }
            };
            cDown.start();
            ButtonHander();
            //CDown();
        } else {
            cDown.cancel();
            Intent intent = new Intent(getBaseContext(), Summary.class);
            //intent.putExtra("questions", questions);
            intent.putExtra("corQ", corQ);
            intent.putExtra("max", chq);
            startActivity(intent);
        }
        //Log.d("QE", "Question Engine called");
    }
    public void CheckAnswer () {
        String [] split_question = SplitQuestion(questions);
        if (split_question[0].equals("single")) {
            RadioGroup rg = (RadioGroup) findViewById(R.id.radio_group);
            int selectedId = rg.getCheckedRadioButtonId();
            RadioButton answer = (RadioButton) findViewById(selectedId);
            if (rg.getCheckedRadioButtonId() == -1) {
                //Log.d("ChA", "No answer was chosen");
            }
            else if (answer.getText().equals(split_question[6])) {
                corQ = corQ +1;
               // Log.d("ChA", "Answer is correct");
            }
            else {
                //Log.d("ChA", "Answer is not correct");
            }
        }
        else if (split_question[0].equals("multi")) {
            CheckBox answer_one_box = (CheckBox) findViewById(R.id.answer_one_box);
            CheckBox answer_two_box = (CheckBox) findViewById(R.id.answer_two_box);
            CheckBox answer_three_box = (CheckBox) findViewById(R.id.answer_three_box);
            CheckBox answer_four_box = (CheckBox) findViewById(R.id.answer_four_box);
            boolean cb1 = Boolean.parseBoolean(split_question[6]);
            boolean cb2 = Boolean.parseBoolean(split_question[7]);
            boolean cb3 = Boolean.parseBoolean(split_question[8]);
            boolean cb4 = Boolean.parseBoolean(split_question[9]);
            if (answer_one_box.isChecked() == cb1 && answer_two_box.isChecked() == cb2 && answer_three_box.isChecked() == cb3 && answer_four_box.isChecked() == cb4) {
                corQ = corQ +1;
               // Log.d("ChA", "Answer is correct");
            }
            else {
                //Log.d("ChA", "Answer is not correct");
            }
        }
    }
    public void ButtonHander () {
        Button nextQ = (Button) findViewById(R.id.apply_answer);
        nextQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAnswer();
                curQ = curQ + 1;
                cDown.cancel();
                QuestionEngine();
            }
        });
    }

}
