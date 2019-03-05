package com.app.uzmav.chillfam;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartUp extends AppCompatActivity {
    
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

        ConstraintLayout constraintLayout = findViewById(R.id.layout);
        AnimationDrawable mAnimation = (AnimationDrawable) constraintLayout.getBackground();
        mAnimation.setEnterFadeDuration(2000);
        mAnimation.setExitFadeDuration(2500);
        mAnimation.start();


        //google maps api Button
        btnStart = (Button) findViewById(R.id.start); // external button on the food side
        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openApp();
            }
        });
    }

    private void openApp() {
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
}
