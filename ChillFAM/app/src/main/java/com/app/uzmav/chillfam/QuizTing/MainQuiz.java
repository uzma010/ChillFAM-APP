package com.app.uzmav.chillfam.QuizTing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.uzmav.chillfam.QuizTing.QuizActivity;
import com.app.uzmav.chillfam.R;

public class MainQuiz extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;

    //highscore stuff
    public static  final String SHARE_ =  "Share";
    public static final String HIGH_SCRE = "KeyHIGHSCR";

    private TextView TxtHighScr;
    private int mHighScre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quiz);

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
        Intent intent = new Intent(this, QuizActivity.class);
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
