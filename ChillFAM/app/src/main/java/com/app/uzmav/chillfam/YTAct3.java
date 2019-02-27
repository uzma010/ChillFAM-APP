package com.app.uzmav.chillfam;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.uzmav.chillfam.AdapterYT.MyCustomAdapter;
import com.app.uzmav.chillfam.ModelYT.VideoDetails;
import com.app.uzmav.chillfam.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class YTAct3 extends AppCompatActivity{


    //global
    public static final String URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCiP6wD_tYlYLYh3agzbByWQ&key=AIzaSyABhHQyK_RDjxVMzJFUn1oJUSs8U5uWagA"; //maxResults=5&

    //variables
    public ArrayList<VideoDetails> mVideoDetailsArrayList;
    public MyCustomAdapter myCustomAdapter;


    //JSON objects
    public JSONObject mJasonObject1, mJasonObject2, mJasonVideoID, mJasonSnippet, mJasonObjectDefault;
    public JSONArray mJasonArray;



    //widget
    // Button btnPlay;

    public ListView videoList3;
    private String video_ID;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ytact_3);

        Log.d(TAG,"onCreate: starting");

        videoList3 = (ListView) findViewById(R.id.videoList3);

        mVideoDetailsArrayList = new ArrayList<>();

        myCustomAdapter = new MyCustomAdapter(this,mVideoDetailsArrayList);

        displayVideos();

    }



    private void displayVideos() {

        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest mStringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    mJasonObject1 = new JSONObject(response);
                    mJasonArray = mJasonObject1.getJSONArray("items"); // here

                    for (int i = 0; i < mJasonArray.length(); i++){

                        mJasonObject2 = mJasonArray.getJSONObject(i);
                        mJasonVideoID = mJasonObject2.getJSONObject("id");
                        mJasonSnippet = mJasonObject2.getJSONObject("snippet");
                        mJasonObjectDefault = mJasonSnippet.getJSONObject("thumbnails").getJSONObject("medium");

                        video_ID = mJasonVideoID.getString("videoId"); //videoId to playlist Id mJasonSnippet.getString("videoId")

                        String titleHead = mJasonSnippet.getString("title");
                        String description = mJasonSnippet.getString("description");
                        String url = mJasonObjectDefault.getString("url");

                        mVideoDetailsArrayList.add(new VideoDetails(video_ID,titleHead,description,url));



                    }

                    videoList3.setAdapter(myCustomAdapter);
                    myCustomAdapter.notifyDataSetChanged();

                    Toast.makeText(getApplicationContext(), "YouTube Displaying", Toast.LENGTH_LONG).show();

                }
                catch(JSONException e){
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();//error.getMessage()
                Log.d(TAG, "ErrorListener: Error Report");

            }
        }
        );

        mRequestQueue.add(mStringRequest);



    }


}












