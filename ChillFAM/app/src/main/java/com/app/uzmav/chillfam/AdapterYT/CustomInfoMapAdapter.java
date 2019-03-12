package com.app.uzmav.chillfam.AdapterYT;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.app.uzmav.chillfam.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoMapAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private final Context mContext;

    public CustomInfoMapAdapter(Context mContext) {
        this.mContext = mContext;
        mWindow = LayoutInflater.from(mContext).inflate(R.layout.custom_mark_layout, null);
    }


    private void windowText(Marker marker, View v){

        String TmTitle = marker.getTitle();
        TextView mTextV = (TextView) v.findViewById(R.id.title);

        if (!TmTitle.equals("")){
            mTextV.setText(TmTitle);

        }

        String TmSnippet = marker.getSnippet();
        TextView mSnippetV = (TextView) v.findViewById(R.id.snippet);

        if (!TmSnippet.equals("")){
            mSnippetV.setText(TmSnippet);

        }

    }

    @Override
    public View getInfoWindow(Marker marker) {
        windowText(marker, mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        windowText(marker, mWindow);
        return mWindow;
    }
}
