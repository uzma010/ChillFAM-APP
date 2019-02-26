package com.app.uzmav.chillfam;

import android.app.Activity;

import com.app.uzmav.chillfam.AdapterYT.MyCustomAdapter;
import com.app.uzmav.chillfam.ModelYT.VideoDetails;

import java.util.ArrayList;

public class YouTubeConfig extends MyCustomAdapter {
/*    public YouTubeConfig(){

    }*/
    public static final String API_KEY = "AIzaSyABhHQyK_RDjxVMzJFUn1oJUSs8U5uWagA";

    //public static final String VIDEO_ID = "PL8ceqcZYn9V5aibrIvuaSlk-Pgd7cwjdj";

    public YouTubeConfig(Activity mActivity, ArrayList<VideoDetails> mVideoDetailsArrayList) {
        super(mActivity, mVideoDetailsArrayList);
    }

    public static String getApiKey(){
        return API_KEY;
    }



}
