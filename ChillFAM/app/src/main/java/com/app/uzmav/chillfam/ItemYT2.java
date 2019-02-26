package com.app.uzmav.chillfam;

public class ItemYT2 {

    //variables
    public String VIDEO_ID, TITLE, DESCRIPTION, URL;

    //constructor


    public ItemYT2(String VIDEO_ID, String TITLE, String DESCRIPTION, String URL) {
        this.VIDEO_ID = VIDEO_ID;
        this.TITLE = TITLE;
        this.DESCRIPTION = DESCRIPTION;
        this.URL = URL;
    }

    //global constructor
    public ItemYT2(){

    }


    public String getVIDEO_ID() {
        return VIDEO_ID;
    }

    public void setVIDEO_ID(String VIDEO_ID) {
        this.VIDEO_ID = VIDEO_ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
