package com.app.uzmav.chillfam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activities extends AppCompatActivity {

    private Button buttips, btnyoutube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);



        //Tips Button
        buttips = (Button) findViewById(R.id.button9); // external button on the activities side
        buttips.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openTips();
            }
        });

        //youtube Button
        btnyoutube = (Button) findViewById(R.id.button8); // external button on the activities side
        btnyoutube.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openYoutube();
            }
        });
    }

    // open tips page
    public void openTips() {
        Intent intent = new Intent(this, Tips.class);
        startActivity(intent);
    }

    // open youtube page
    public void openYoutube() {
        Intent intent = new Intent(this, YouTubeAct.class);
        startActivity(intent);
    }
}
