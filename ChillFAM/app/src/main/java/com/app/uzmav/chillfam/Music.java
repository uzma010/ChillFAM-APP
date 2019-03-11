package com.app.uzmav.chillfam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Music extends AppCompatActivity implements View.OnClickListener {


    public static String PLAYLIST_ID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        Button mBtnChart = findViewById(R.id.btnMChart);
        Button mBtnHip = findViewById(R.id.btnMHip);
        Button mBtnChill = findViewById(R.id.btnMChill);
        Button mBtnClass = findViewById(R.id.btnMClass);
        Button mBtnRock = findViewById(R.id.btnMRock);
        Button mBtnIRock = findViewById(R.id.btnMIndRock);
        Button mBtnMellow = findViewById(R.id.btnMMellow);
        Button mBtnHappy = findViewById(R.id.btnMHappy);
        Button mBtnPop = findViewById(R.id.btnMPop);
        Button mBtnWrk = findViewById(R.id.btnMWorkout);


        mBtnChart.setOnClickListener(this);
        mBtnHip.setOnClickListener(this);
        mBtnChill.setOnClickListener(this);
        mBtnClass.setOnClickListener(this);
        mBtnRock.setOnClickListener(this);
        mBtnIRock.setOnClickListener(this);
        mBtnMellow.setOnClickListener(this);
        mBtnHappy.setOnClickListener(this);
        mBtnPop.setOnClickListener(this);
        mBtnWrk.setOnClickListener(this);

    }

    public static String getSPlayID(){

        return PLAYLIST_ID;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnMChart:
                PLAYLIST_ID = "spotify:playlist:37i9dQZF1DX2sUQwD7tbmL";

                break;

            case R.id.btnMHip:
                PLAYLIST_ID = "spotify:user:1245804746:playlist:2LGdSw8ZuOIXirHHohi8cw";
                break;

            case R.id.btnMChill:
                PLAYLIST_ID = "spotify:user:1245804746:playlist:2LGdSw8ZuOIXirHHohi8cw";
                break;

            case R.id.btnMClass:
                PLAYLIST_ID = "spotify:user:1245804746:playlist:2LGdSw8ZuOIXirHHohi8cw";
                break;

            case R.id.btnMRock:
                PLAYLIST_ID = "spotify:user:1245804746:playlist:2LGdSw8ZuOIXirHHohi8cw";
                break;

            case R.id.btnMIndRock:
                PLAYLIST_ID = "spotify:user:1245804746:playlist:2LGdSw8ZuOIXirHHohi8cw";
                break;

            case R.id.btnMMellow:
                PLAYLIST_ID = "spotify:user:1245804746:playlist:2LGdSw8ZuOIXirHHohi8cw";
                break;

            case R.id.btnMHappy:
                PLAYLIST_ID = "spotify:user:1245804746:playlist:2LGdSw8ZuOIXirHHohi8cw";
                break;

            case R.id.btnMPop:
                PLAYLIST_ID = "spotify:user:1245804746:playlist:2LGdSw8ZuOIXirHHohi8cw";
                break;

            case R.id.btnMWorkout:
                PLAYLIST_ID = "spotify:user:1245804746:playlist:2LGdSw8ZuOIXirHHohi8cw";
                break;
        }


        Intent intent = new Intent(this, MainSpotify.class);
        startActivity(intent);

    }
}
