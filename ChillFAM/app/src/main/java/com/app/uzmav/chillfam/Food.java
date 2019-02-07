package com.app.uzmav.chillfam;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Food extends AppCompatActivity {

    private Button butbrain, butmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
       /** //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

       // FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
         //   @Override
         //   public void onClick(View view) {
         //       Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
         //               .setAction("Action", null).show();
         //   }
       // });
    //}
**/



        //Brain Food Button
        butbrain = (Button) findViewById(R.id.button5); // external button on the food side
        butbrain.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openBrain();
            }
        });

        //google maps api Button
        butmap = (Button) findViewById(R.id.button6); // external button on the food side
        butmap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openMaps();
            }
        });
    }

    // open brain Food page
    public void openBrain() {
        Intent intent = new Intent(this, Tips.class);
        startActivity(intent);
    }

    // open google maps api page
    public void openMaps() {
        Intent intent = new Intent(this, Maps.class);
        startActivity(intent);
    }
}
