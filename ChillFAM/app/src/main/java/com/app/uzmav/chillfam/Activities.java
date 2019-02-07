package com.app.uzmav.chillfam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activities extends AppCompatActivity {

    private Button buttips;

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
    }

    // open tips page
    public void openTips() {
        Intent intent = new Intent(this, Tips.class);
        startActivity(intent);
    }
}
