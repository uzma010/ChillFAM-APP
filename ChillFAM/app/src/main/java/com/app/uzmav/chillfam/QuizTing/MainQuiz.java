package com.app.uzmav.chillfam.QuizTing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.uzmav.chillfam.QuizTing.QuizActivity;
import com.app.uzmav.chillfam.R;

public class MainQuiz extends AppCompatActivity {

    //constant
    private static final int REQUEST_CODE = 1;
    public static final String EXTRA_DIFF = "ExtraDIFF";

    //highscore stuff
    public static  final String SHARE_ =  "Share";
    public static final String HIGH_SCRE = "KeyHIGHSCR";

    //widgets
    private TextView TxtHighScr;
    private Spinner SpinDiff;

    private int mHighScre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quiz);

        SpinDiff = findViewById(R.id.SpinDiff); // change this?

        String [] mDiffLevels = Question.getDIffLevls(); //we call this questions on the class itself not on the object

        ArrayAdapter<String> mAdapterDifficulty = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mDiffLevels);
        mAdapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // adjust the look of the drop down menu to make it look better

        SpinDiff.setAdapter(mAdapterDifficulty); // set adapter with ou spinner - get our spinner difficulty levels and place them in the spinner


        TxtHighScr = findViewById(R.id.TxtHigh);


        LoadHS();


        Button BtnStartQuiz = findViewById(R.id.btnStartQuiz);

        BtnStartQuiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openQuiz();
            }
        });


    }

    private void openQuiz() {

        //need to pass the difficulty level to the next page
        String mDifficultyChosen = SpinDiff.getSelectedItem().toString(); // gets whatever item the user selected on the spinner


        Intent intent = new Intent(this, QuizActivity.class);

        intent.putExtra(EXTRA_DIFF, mDifficultyChosen);

        startActivityForResult(intent, REQUEST_CODE); // calls the result back from the start
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE){
            if(resultCode== RESULT_OK){
                int scre = data.getIntExtra(QuizActivity.ADD_SCR, 0);
                if(scre > mHighScre){
                    NewHighscore(scre);
                }
            }

        }
    }

    private void LoadHS(){
        SharedPreferences prefs = getSharedPreferences(SHARE_, MODE_PRIVATE);
        mHighScre = prefs.getInt(HIGH_SCRE, 0);
        TxtHighScr.setText("Highscore: " + mHighScre);

    }

    private void NewHighscore(int NewHS) {
        mHighScre = NewHS;
        TxtHighScr.setText("Highscore: " + mHighScre);

        SharedPreferences prefs = getSharedPreferences(SHARE_, MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(HIGH_SCRE, mHighScre);
        editor.apply();
    }
}
