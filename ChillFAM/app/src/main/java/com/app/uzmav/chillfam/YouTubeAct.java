package com.app.uzmav.chillfam;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import static android.support.constraint.Constraints.TAG;

public class YouTubeAct extends YouTubeBaseActivity  implements YouTubePlayer.OnInitializedListener, YouTubePlayer.PlaybackEventListener, YouTubePlayer.PlayerStateChangeListener{


    //global
    public static final String API_KEY = "AIzaSyABhHQyK_RDjxVMzJFUn1oJUSs8U5uWagA";
    public static final String VIDEO_ID = "jrwyZPrs";

    //variables
    public YouTubePlayerView mplayerView;
    public YouTubePlayer.OnInitializedListener mOnInitializedListener;

    //widget
  //  Button btnPlay;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        Log.d(TAG,"onCreate: starting");

      //  btnPlay = (Button) findViewById(R.id.btnPlay);
        mplayerView = (YouTubePlayerView)findViewById(R.id.playerview);
        mplayerView.initialize(YouTubeConfig.getApiKey(), this);


/*
        mOnInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {


                youTubePlayer.cuePlaylist("RD9lIoSHRqvkg");

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };*/

    /*    btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mplayerView.initialize(YouTubeConfig.getApiKey(), mOnInitializedListener);

            }
        });*/


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        youTubePlayer.setPlayerStateChangeListener(this);
        youTubePlayer.setPlaybackEventListener(this);

        if(!b){
            youTubePlayer.cuePlaylist("RD9lIoSHRqvkg");
        }

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onPlaying() { //

    }

    @Override
    public void onPaused() {//

    }

    @Override
    public void onStopped() {//

    }

    @Override
    public void onBuffering(boolean b) {//

    }

    @Override
    public void onSeekTo(int i) {//

    }

    @Override
    public void onLoading() {//

    }

    @Override
    public void onLoaded(String s) {//

    }

    @Override
    public void onAdStarted() {//

    }

    @Override
    public void onVideoStarted() {//

    }

    @Override
    public void onVideoEnded() {//

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {//

    }
}
