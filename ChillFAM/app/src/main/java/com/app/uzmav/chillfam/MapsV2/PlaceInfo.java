package com.app.uzmav.chillfam.MapsV2;

import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;

public class PlaceInfo {

    private String Rname, Raddr, Rboth, RID, RphnNumb;
    private LatLng RLatLngLoc;
    private double RRating;
    private Uri RWebURL;

    public PlaceInfo(String rname, String raddr, String rboth, String RID, String rphnNumb, LatLng RLatLngLoc, double RRating, Uri RWebURL) {
        this.Rname = rname;
        this.Raddr = raddr;
        this.Rboth = rboth;
        this.RID = RID;
        this.RphnNumb = rphnNumb;
        this.RLatLngLoc = RLatLngLoc;
        this.RRating = RRating;
        this.RWebURL = RWebURL;
    }

    public PlaceInfo() {

    }

    public String getPName() {
        return Rname;
    }

    public void setPName(String rname) {
        Rname = rname;
    }

    public String getAddr() {
        return Raddr;
    }

    public void setAddr(String raddr) {
        Raddr = raddr;
    }

    public String getRBoth() {
        return Rboth;
    }

    public void setBRboth(String rboth) {
        Rboth = rboth;
    }

    public String getRID() {
        return RID;
    }

    public void setRID(String RID) {
        this.RID = RID;
    }

    public String getPhnNumb() {
        return RphnNumb;
    }

    public void setPhnNumb(String rphnNumb) {
        RphnNumb = rphnNumb;
    }

    public LatLng getLatLngLoc() {
        return RLatLngLoc;
    }

    public void setLatLngLoc(LatLng RLatLngLoc) {
        this.RLatLngLoc = RLatLngLoc;
    }

    public double getRating() {
        return RRating;
    }

    public void setRating(double RRating) {
        this.RRating = RRating;
    }

    public Uri getWebURL() {
        return RWebURL;
    }

    public void setWebURL(Uri RWebURL) {
        this.RWebURL = RWebURL;
    }

    @Override
    public String toString() {
        return "PlaceInfo{" +
                "Rname='" + Rname + '\'' +
                ", Raddr='" + Raddr + '\'' +
                ", Rboth='" + Rboth + '\'' +
                ", RID='" + RID + '\'' +
                ", RphnNumb='" + RphnNumb + '\'' +
                ", RLatLngLoc=" + RLatLngLoc +
                ", RRating=" + RRating +
                ", RWebURL=" + RWebURL +
                '}';
    }
}
