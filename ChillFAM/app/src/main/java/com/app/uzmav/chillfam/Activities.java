package com.app.uzmav.chillfam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activities extends AppCompatActivity {

    private Button btntips, btnyoutube, btnMotivate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);




        //Youtube Button for motivational ted talks
        btnMotivate = (Button)findViewById(R.id.btnVid2);
        btnMotivate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openYTMot();
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

        //home exersize Button
        btntips = (Button) findViewById(R.id.button9); // external button on the activities side
        btntips.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openExer();
            }
        });
    }


    // open ted talks on procrastination
    private void openYTMot() {
        Intent i = new Intent(this, MainYT2.class);
        startActivity(i);
    }

    // open tips page
    public void openExer() {
        Intent intent = new Intent(this, YTAct3.class);
        startActivity(intent);
    }

    // open youtube page
    public void openYoutube() {
        Intent intent = new Intent(this, YouTubeAct.class);
        startActivity(intent);
    }
}
