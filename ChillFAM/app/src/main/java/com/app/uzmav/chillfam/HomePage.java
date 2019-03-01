package com.app.uzmav.chillfam;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class HomePage extends AppCompatActivity {

    private static final String TAG = "HomePage";

    private static final int ERROR_DIALOG_REQUEST = 9001;



    private Button butfood, butactivity, butmusic, buttips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        if (isServiceOK()) {

            button();

        }
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener();

    }

        public void button () {

            // ADD BUTTONS HERE FROM HOMEPAGE


            // Food button

            butfood = (Button) findViewById(R.id.button);
            butfood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openFood();
                }
            });


            // activities button
            butactivity = (Button) findViewById(R.id.button2);
            butactivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openActivities();
                }
            });


            //Music Button
            butmusic = (Button) findViewById(R.id.button3);
            butmusic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openMusic();
                }
            });


            //Tips Button
            buttips = (Button) findViewById(R.id.button4);
            buttips.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openTips();
                }
            });


        }




        //OPne classes

            //open food page
        public void openFood(){
            Intent intent = new Intent(this,  Food.class);
            startActivity(intent);
        }

            // open activity page

        public void openActivities(){
            Intent intent = new Intent(this,  Activities.class);
            startActivity(intent);
        }

            // open music page
        public void openMusic() {
            Intent intent = new Intent(this, MainSpotify.class);
            startActivity(intent);
        }


            // open tips page
        public void openTips() {
            Intent intent = new Intent(this, Tips.class);
            startActivity(intent);
        }









    //public void buttonOnClick(View v){
    //    Button button = (Button) v;
      //  ((Button) v).setText("clicked");}



    public boolean isServiceOK(){
        Log.d(TAG, "isServiceOK: checking Google Services version");

        int available  = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(HomePage.this);

        if(available == ConnectionResult.SUCCESS){
            // checks to see if the version of android is correct, everything is okay and the user can make map requests

            Log.d(TAG, "isServiceOK: Google Play Services is working");

            return true;

        }

        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){

            // error has occured but we can resolve it

            Log.d(TAG, "isServiceOK: an error has occured but but we can fix it");

            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(HomePage.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }

        else{

            //error that we cannot resolve

            Toast.makeText(this, "You cannot make map requests at this time", Toast.LENGTH_SHORT).show();
        }

        return false;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
