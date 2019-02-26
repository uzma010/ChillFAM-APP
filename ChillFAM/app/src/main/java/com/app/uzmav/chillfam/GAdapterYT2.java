package com.app.uzmav.chillfam;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GAdapterYT2 extends RecyclerView.Adapter<GAdapterYT2.GAdapterYT2ViewHolder> {

    private Context mContext;
    private ArrayList<ItemYT2> mExampleList;

    public GAdapterYT2 (Context context, ArrayList<ItemYT2> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public GAdapterYT2ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.itemyt2, viewGroup ,false);
        return new GAdapterYT2ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GAdapterYT2ViewHolder gViewHolder, int position) {
        ItemYT2 currentItem = mExampleList.get(position);

        String videoID = currentItem.getVIDEO_ID();
        String titleHead = currentItem.getTITLE();
        String descrption =currentItem.getDESCRIPTION();
        String url = currentItem.getURL();

        gViewHolder.mVidID.setText(videoID);
        gViewHolder.mTextDescription.setText(descrption);
        gViewHolder.mTextHeading.setText(titleHead);
        //Picasso.get().load(url).fit().centerInside().into(gViewHolder.mImageView);
        Picasso.get().load(url).into(gViewHolder.mImageView);


    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public class GAdapterYT2ViewHolder extends RecyclerView.ViewHolder{
        public TextView mVidID;
        public ImageView mImageView;
        public TextView mTextHeading;
        public TextView mTextDescription;


        public GAdapterYT2ViewHolder(View itemView){
            super(itemView);

            mVidID = itemView.findViewById(R.id.vidID);
            mImageView = itemView.findViewById(R.id.ImageVid);
            mTextDescription = itemView.findViewById(R.id.description);
            mTextHeading = itemView.findViewById(R.id.heading);
        }

    }
}
