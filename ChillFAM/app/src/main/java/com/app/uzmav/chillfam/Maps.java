package com.app.uzmav.chillfam;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;

import java.io.IOException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

//import android.support.annotation.Nullable;


    public class Maps extends FragmentActivity implements OnMapReadyCallback {


    private static final String TAG = "Maps";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1212;
    private static final float DEFAULT_ZOOM = 15f;

    //variables
    public boolean mLocationPermissionGranted = false;
    public GoogleMap mMap;
    public FusedLocationProviderClient mFusedLocationProviderClient;

    //widget
    private EditText mSearchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // @Nullable  add before bundle?
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        /**SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
         .findFragmentById(R.id.map);
         mapFragment.getMapAsync(this);**/

        mSearchText = (EditText) findViewById(R.id.input_search);

        getLocationPermission();

        initSearch();
    }

    private void initSearch(){
        Log.d(TAG, "initSearch: Initializing search method");

        // override return key that will search therefore no onclick listener

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE || event.getAction() ==KeyEvent.ACTION_DOWN || event.getAction() == KeyEvent.KEYCODE_ENTER    ){

                    //execute the method to search
                    geoSearchLocate();

                }
                return false;
            }
        });
    }
    
    private void geoSearchLocate(){

        Log.d(TAG, "geoSearchLocate: locating");

        String searchString = mSearchText.getText().toString();

        Geocoder geoCoder = new Geocoder(Maps.this);

        List<Address> listAdd = new ArrayList<>();

        try{

            listAdd = geoCoder.getFromLocationName(searchString, 1);

        }catch(IOException e){
            Log.e(TAG, "geoSearchLocate: IOException: "+ e.getMessage() );
        }

        if(listAdd.size()>0){
            Address address = listAdd.get(0);

            Log.d(TAG, "geoSearchLocate: Found Location: " + address.toString());

            //Toast.makeText(this, "Map is ready", Toast.LENGTH_SHORT).show();

        }
    }

    private void getDeviceLocation() {
        Log.d(TAG, "getDeviceLocation: getting the  devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {

            if (mLocationPermissionGranted) {

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: found location");

                            Location currentLocation = (Location) task.getResult();
                            // once we get that location in result - we can move the screen towards that result
                            moveScreen(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM);


                        } else {
                            Log.d(TAG, "onComplete: Current location cannot be found");
                            Toast.makeText(Maps.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }

        } catch (SecurityException e) {
            Log.e(TAG, "getDeviceLocation: SecurityException : " + e.getMessage());
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Toast.makeText(this, "Map is ready", Toast.LENGTH_SHORT).show(); // allows the tester to see that the map is ready - and working correctly
        Log.d(TAG, "onMapReady: map is ready");

        mMap = googleMap;

        if (mLocationPermissionGranted) { // need to check if the gps is on and location is permitted

            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {


                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

        }
    }

    private void moveScreen(LatLng latlng, float zoom){

        Log.e(TAG, "moveScreen: moving the screen to : lat:" + latlng.latitude + " lng: " + latlng.longitude ); // just so we know where is is moving the camera to
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,zoom));
    }

    private void initMap(){
        Log.d(TAG, "initMap: initialising map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(Maps.this);

    }


// needs to check if we have users permission to access location
    private void getLocationPermission(){
        // need to create a string array of the permissions that we want add log so tester can see in LOGCAT ANDROID MONITOR
        Log.d(TAG, "getLocationPermission: getting location permission");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                initMap();

            }
            else {// we would need to ask permission
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);


            }
        }
        else {// we would need to ask permission
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);


        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;

        switch(requestCode){

            case LOCATION_PERMISSION_REQUEST_CODE:{
                //if permission was granted
                if(grantResults.length> 0 ){
                    for (int i = 0; i< grantResults.length; i++){
                        if(grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionGranted = true;

                    // initialise the map
                    initMap();
                }
            }
        }
    }



}









