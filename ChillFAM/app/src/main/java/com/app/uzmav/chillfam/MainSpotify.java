package com.app.uzmav.chillfam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.spotify.android.appremote.api.ConnectionParams;
import com.spotify.android.appremote.api.Connector;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import com.spotify.protocol.client.Subscription;
import com.spotify.protocol.types.PlayerState;
import com.spotify.protocol.types.Track;

public class MainSpotify extends AppCompatActivity {

    private static final String CLIENT_ID = "ce57821310c14bb687d0cfc173a33d64";
    private static final String REDIRECT_URI = "com.app.uzmav.chillfam://callback";
    private SpotifyAppRemote mSpotifyAppRemote;

    private long mBackTimer;

    //widget
    private TextView mText;
    //private Button mBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_spotify);

        mText = (TextView) findViewById(R.id.textSpotifyInfo);



    }

    public void openNotify() {

        NotifyDialog mNotification = new NotifyDialog();
        mNotification.show(getSupportFragmentManager(), "Notify Dialog");
    }

    @Override
    protected void onStart() {
        super.onStart();
        // We will start writing our code here.
        ConnectionParams connectionParams = new ConnectionParams.Builder(CLIENT_ID).setRedirectUri(REDIRECT_URI).showAuthView(true).build();

        SpotifyAppRemote.connect(this, connectionParams,
                new Connector.ConnectionListener() {

                    public void onConnected(SpotifyAppRemote spotifyAppRemote) {
                        mSpotifyAppRemote = spotifyAppRemote;
                        Log.d("MainSpotifyActivity", "Connected! Yay!");
                        Toast.makeText(getApplicationContext(), "Spotify is Connected ", Toast.LENGTH_LONG).show();

                        // Now you can start interacting with App Remote
                        connected();
                        openNotify();

                        //add pop up notification here

                    }

                    public void onFailure(Throwable throwable) {
                        Log.e("MySpotifyActivity", throwable.getMessage(), throwable);

                        // Something went wrong when attempting to connect! Handle errors here
                    }
                });

    }

    private void connected() {
        // Then we will write some more code here.
        // playing a playlist
        mSpotifyAppRemote.getPlayerApi().play(Music.getSPlayID());


        // Subscribe to PlayerState
        mSpotifyAppRemote.getPlayerApi().subscribeToPlayerState();



    }

    @Override
    protected void onStop() {
        super.onStop();
        SpotifyAppRemote.disconnect(mSpotifyAppRemote);
    }

    @Override
    public void onBackPressed() {

        if(mBackTimer + 2000 > System.currentTimeMillis()){

            onStop();
            finish();


        }else{
            Toast.makeText(this, "Press Back again to finish", Toast.LENGTH_SHORT).show();
        }

        mBackTimer = System.currentTimeMillis();

    }

}
