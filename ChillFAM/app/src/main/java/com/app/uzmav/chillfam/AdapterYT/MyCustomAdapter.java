package com.app.uzmav.chillfam.AdapterYT;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.uzmav.chillfam.ModelYT.VideoDetails;
import com.app.uzmav.chillfam.R;
import com.app.uzmav.chillfam.PlayVideoDetails;
import com.app.uzmav.chillfam.StartVideoActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class MyCustomAdapter extends BaseAdapter {

    Activity mActivity;
    ArrayList<VideoDetails> mVideoDetailsArrayList;


    LayoutInflater mInflater;


     VideoDetails mVideoDetails;

    // widgets
    private ImageView mImageVid;
    private TextView mTextTitle;
    private LinearLayout mLinerLayout;
   // private OnItemClickListener mListener;

    public static String VIDEO;


    //constructor
    public MyCustomAdapter(Activity mActivity, ArrayList<VideoDetails> mVideoDetailsArrayList) {
        this.mActivity = mActivity;
        this.mVideoDetailsArrayList = mVideoDetailsArrayList;
    }


    @Override
    public int getCount() {

        return this.mVideoDetailsArrayList.size();
    }

    @Override
    public Object getItem(int position) {

        return this.mVideoDetailsArrayList.get(position);//i
    }

    @Override
    public long getItemId(int position) {

        return (long)position;
    }

    public static String getVideoID(){

        // mVideoDetails.getVIDEO_ID()

        return VIDEO;




    }

    @Override
    public View getView(final int position,  View convertView, ViewGroup parent) {

        if (mInflater == null){
            mInflater = this.mActivity.getLayoutInflater();
        }

        if (convertView == null){

            convertView = mInflater.inflate(R.layout.activity_custom, null);

        }

        mImageVid = (ImageView)convertView.findViewById(R.id.ImageVid);
        mTextTitle = (TextView)convertView.findViewById(R.id.heading);
        mLinerLayout = (LinearLayout)convertView.findViewById(R.id.VIDEOS);
        final VideoDetails mVideoDetails =  mVideoDetailsArrayList.get(position);

        Log.d("chief", "message of pos: " + mVideoDetails.getTITLE() +" --  VideoID: "+ mVideoDetails.getVIDEO_ID());


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int position = getAdapterPosition();

                //mTextTitle = (TextView) v;

                VideoDetails mVideoDetails2 =  mVideoDetailsArrayList.get(position);
                Intent i = new Intent(mActivity, PlayVideoDetails.class); //StartVideoActivity.class);
                i.putExtra("videoId", VIDEO = mVideoDetails2.getVIDEO_ID());//name = videoId to playlistId

                mActivity.startActivity(i);

                Log.d("MYCOMPACTACTIVITY", "Title: " + mVideoDetails.getTITLE());
                Log.d("MYCOMPACTACTIVITY", "Title 2: " + mVideoDetails2.getTITLE());
                Log.d("MYCOMPACTACTIVITY","VideoID: " + mVideoDetails.getVIDEO_ID());
                Log.d("MYCOMPACTACTIVITY","VideoID 2: " + mVideoDetails2.getVIDEO_ID());
                Log.d("MYCOMPACTACTIVITY","URL: " + mVideoDetails.getURL());
                Log.d("MYCOMPACTACTIVITY","URL 2: " + mVideoDetails2.getURL());
                //Log.d("MYCOMPACTACTIVITY3","VIEW V: " +  v.findViewById(R.id.ImageVid));

            }

        });



        Picasso.get().load(mVideoDetails.getURL()).into(mImageVid);

        mTextTitle.setText(mVideoDetails.getTITLE());

        return convertView;
    }


}





