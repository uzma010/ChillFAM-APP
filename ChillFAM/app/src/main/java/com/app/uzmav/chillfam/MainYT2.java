package com.app.uzmav.chillfam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class MainYT2 extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private GAdapterYT2 mAdapter;
    private ArrayList<ItemYT2> mVideoArrayList;
    private RequestQueue mRequestQueue; // needed for volley

    public ItemYT2 mVD;



    //JSON variables
    //JSON objects
    public JSONObject  mJasonObject2, mJasonVideoID, mJasonSnippet, mJasonObjectDefault, mJasonVidURL;
    public JSONArray mJasonArray;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_yt2);

        mRecyclerView = findViewById(R.id.recycleView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mVideoArrayList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        mAdapter = new GAdapterYT2(MainYT2.this, mVideoArrayList);
        mRecyclerView.setAdapter(mAdapter);

        parseJSON();
        


    }

    private void parseJSON() {

        String URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&channelId=UCFKE7WVJfvaHW5q283SxchA&maxResults=5&key=AIzaSyABhHQyK_RDjxVMzJFUn1oJUSs8U5uWagA";

        JsonObjectRequest jRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //JSONObject mJ = new JSONObject(response);

                            JSONArray mJArray = response.getJSONArray("item");

                            for (int i = 0; i < mJArray.length(); i++){

                                mJasonObject2 = mJArray.getJSONObject(i);
                                mJasonVideoID = mJasonObject2.getJSONObject("id");
                                mJasonSnippet = mJasonObject2.getJSONObject("snippet");
                                mJasonObjectDefault = mJasonSnippet.getJSONObject("thumbnails");
                                mJasonVidURL = mJasonObjectDefault.getJSONObject("medium");

                                String videoID = mJasonVideoID.getString("videoId");
                                String titleHead = mJasonSnippet.getString("title");
                                String description = mJasonSnippet.getString("description");
                                String url = mJasonVidURL.getString("url");

                                mVideoArrayList.add(new ItemYT2(videoID,titleHead,description,url));


                                /* mVD = new ItemYT2();

                                mVD.setVIDEO_ID(mJasonVideoID.getString("videoId")); //video_ID);
                                mVD.setTITLE(mJasonSnippet.getString("title"));
                                mVD.setDESCRIPTION(mJasonSnippet.getString("description"));
                                mVD.setURL(mJasonVidURL.getString("url"));

                                mVideoArrayList.add(mVD);*/



                            }

                            mAdapter = new GAdapterYT2(MainYT2.this, mVideoArrayList);
                            mRecyclerView.setAdapter(mAdapter);


                        } catch (JSONException e) {

                           // e.printStackTrace();

                        }


                    }
                }, new Response.ErrorListener(){
            @Override
            public  void onErrorResponse(VolleyError error){

                error.printStackTrace();
            }

        });

        mRequestQueue.add(jRequest);


    }
}
