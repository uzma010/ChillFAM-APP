package com.app.uzmav.chillfam.QuizTing;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.uzmav.chillfam.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizActivity extends AppCompatActivity {

    //Constants
    public static  final String ADD_SCR = "AdditionalScore";
    private static final long CNT_INT_MILIS = 20000;


    //const for runtime layout
    private static final String KY_SCORE = "KeyScore";
    private static final String KY_Q_NUMB = "KeyQuestionNumber";
    private static final String KY_TIME_MILLS = "KeyTimeInMilis";
    private static final String KY_ANS = "KeyAnswered";
    private static final String KY_Q_LIST = "KeyQList";


    //widgets variables
    private TextView Txtscore, TxtQuestion, TxtQuestionCount , TxtTimer;


    private RadioGroup RDGroup;
    private RadioButton RD1, RD2, RD3;
    private Button BtnConfirmNext;


    private ColorStateList txtDefaultColour; // change the colour to make it back to default

    //timer variables
    private ColorStateList cntTxtDefaultColour;
    private CountDownTimer mCountDTimer;
    private long mTimeLeftMillis;


    //question variables
    private int questionCounter, questionTotal;
    private Question currentQuestion;
    private ArrayList<Question> questionList;

    private int mScore;
    private boolean qAnswered;


    private long mBackTimer;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);



        Txtscore = findViewById(R.id.Txtscore);
        TxtQuestion = findViewById(R.id.TextQuestion);
        TxtQuestionCount = findViewById(R.id.TxtQuestionCount);
        TxtTimer = findViewById(R.id.textTimer);
        RDGroup = findViewById(R.id.RadioGroup);
        RD1 = findViewById(R.id.radioBtn1);
        RD2 = findViewById(R.id.radioBtn2);
        RD3 = findViewById(R.id.radioBtn3);
        BtnConfirmNext = findViewById(R.id.btnConfirmNext);

        txtDefaultColour = RD1.getTextColors(); // gets text colour
        cntTxtDefaultColour = TxtTimer.getTextColors();




        if(savedInstanceState == null) { // only not null if there is a state to be restored.
            QuizDB dbHelp = new QuizDB(this);
            questionList = dbHelp.getDiffQuestions("MEDIUM"); // creates db for the first time dbHelp.getAllTheQuestions() --checking to see if new db request is created based on difficulty

            questionTotal = questionList.size();
            Collections.shuffle(questionList); // gives us a random question

            ShowNextQuestion();
        } else{
            questionList = savedInstanceState.getParcelableArrayList(KY_Q_LIST);
            if (questionList == null){//allows null pointer fto be avoided in the error smh
                finish();
            }
            questionTotal = questionList.size(); // nullpointer exeption but our question list will never be null sooo IDK
            questionCounter = savedInstanceState.getInt(KY_Q_NUMB);
            currentQuestion = questionList.get(questionCounter - 1); // because our qcounter is always one ahead of our question state
            mScore= savedInstanceState.getInt(KY_SCORE);
            mTimeLeftMillis = savedInstanceState.getLong(KY_TIME_MILLS);
            qAnswered = savedInstanceState.getBoolean(KY_ANS);


            //all our valuues are stored if our questions where answer or if it was answered then we can show the correct question
            if(!qAnswered ){
                StartCountDwn();
            }else{
                //need to make sure the colour text is resent
                UpdateCNTDWNTxt();
                Solution(); //updatedyehh
            }
        }




        BtnConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!qAnswered){
                    if(RD1.isChecked() || RD2.isChecked()|| RD3.isChecked()){
                        CheckAns();
                    }else{ // if nothing is checked
                        Toast.makeText(QuizActivity.this, "Please Select an Answer", Toast.LENGTH_SHORT).show();
                    }
                } else{
                   // ShowNextQuestion();
                    ShowNextQuestion();

                }
            }
        });
        



    }



    private void ShowNextQuestion() {
        RD1.setTextColor(txtDefaultColour);
        RD2.setTextColor(txtDefaultColour);
        RD3.setTextColor(txtDefaultColour);

        RDGroup.clearCheck();

        if(questionCounter< questionTotal){
            currentQuestion = questionList.get(questionCounter); // implements question
            TxtQuestion.setText(currentQuestion.getQuestion());
            RD1.setText(currentQuestion.getOptn1());
            RD2.setText(currentQuestion.getOptn2());
            RD3.setText(currentQuestion.getOptn3());

            questionCounter ++; // start at one not 0

            TxtQuestionCount.setText("Question: " + questionCounter + "/" + questionTotal);

            qAnswered = false;

            BtnConfirmNext.setText("Confirm");

            // reset the counter  everytime there is a new question
            mTimeLeftMillis = CNT_INT_MILIS;
            StartCountDwn();


        }else{// when all the questions are finished - finish quiz

            FinishQuiz();
        }
    }

    private void StartCountDwn() {
        mCountDTimer = new CountDownTimer(mTimeLeftMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftMillis = millisUntilFinished;
                UpdateCNTDWNTxt();

            }

            @Override
            public void onFinish() {

                mTimeLeftMillis = 0;
                UpdateCNTDWNTxt();
                CheckAns();// if we have chosen an option it will still choose it

            }
        }.start(); // start upon create


    }

    private void UpdateCNTDWNTxt() {


        int mMins = (int) (mTimeLeftMillis/ 1000) /60;
        int mSeconds = (int) (mTimeLeftMillis/1000) %60;

        String mTime = String.format(Locale.getDefault(), "%02d:%02d", mMins, mSeconds);

        TxtTimer.setText(mTime);

        // change the colour when smaller than 10 seconds
        if (mTimeLeftMillis < 10000){
            TxtTimer.setTextColor(Color.RED);
        }else{
            TxtTimer.setTextColor(cntTxtDefaultColour );
        }
    }


    private void CheckAns() {
        qAnswered = true;

        mCountDTimer.cancel();

        RadioButton RDSelected = findViewById(RDGroup.getCheckedRadioButtonId()); // returns the id of the radio button checked
        int AnsNumb = RDGroup.indexOfChild(RDSelected) + 1;

        if (AnsNumb == currentQuestion.getAnswerNmbr()){

            mScore ++;
            Txtscore.setText("Score: " + mScore);

        }
        
        Solution();
    }

    private void Solution() {

        RD1.setTextColor(Color.RED);
        RD2.setTextColor(Color.RED);
        RD3.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNmbr()){ // changes the colour
            case 1:
                RD1.setTextColor(Color.GREEN);
                TxtQuestion.setText("Answer 1 is Correct");
                break;
            case 2:
                RD2.setTextColor(Color.GREEN);
                TxtQuestion.setText("Answer 2 is Correct");
                break;
            case 3:
                RD3.setTextColor(Color.GREEN);
                TxtQuestion.setText("Answer 3 is Correct");
                break;
        }

        if(questionCounter < questionTotal){
            BtnConfirmNext.setText("NEXT");
        }else{// no mre questions
            BtnConfirmNext.setText("FINISH");
        }
    }


    private void FinishQuiz() {

        Intent resultIntent = new Intent();
        resultIntent.putExtra(ADD_SCR, mScore);

        setResult(RESULT_OK, resultIntent);// default?

        finish();
    }


    @Override
    public void onBackPressed() {

        if(mBackTimer + 2000 > System.currentTimeMillis()){

            finish(); // dont want to save the score if you do then - FinishQuiz():

        }else{
            Toast.makeText(this, "Press Back again to finish", Toast.LENGTH_SHORT).show();
        }

        mBackTimer = System.currentTimeMillis();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mCountDTimer != null){
            mCountDTimer.cancel();

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KY_SCORE, mScore);
        outState.putInt(KY_Q_NUMB, questionCounter);
        outState.putLong(KY_TIME_MILLS, mTimeLeftMillis);
        outState.putBoolean(KY_ANS, qAnswered);
        outState.putParcelableArrayList(KY_Q_LIST, questionList);



    }







}





