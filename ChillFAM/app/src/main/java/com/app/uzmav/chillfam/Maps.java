package com.app.uzmav.chillfam;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.app.uzmav.chillfam.AdapterYT.CustomInfoMapAdapter;
import com.app.uzmav.chillfam.MapsV2.PlaceInfo;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;


import org.w3c.dom.Text;

import java.io.IOException;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



    public class Maps extends FragmentActivity implements OnMapReadyCallback {


    private static final String TAG = "Maps";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1212;
    private static final float DEFAULT_ZOOM = 15f;

    public static final String API_KEY = "AIzaSyABhHQyK_RDjxVMzJFUn1oJUSs8U5uWagA";

    //variables
    public boolean mLocationPermissionGranted = false;
    public GoogleMap mMap;
    public FusedLocationProviderClient mFusedLocationProviderClient;

    //widget
    private TextView mSearchText;
    private ImageView mGPS, mInfo, mNerby;
    private AutocompleteSupportFragment mAutocompleteSupportFragment;

    private Marker mark;

    //trial for fragment gps
    private String Rname, Raddr, Rboth, RID, RphnNumb;
    private LatLng RLatLngLoc;
    private Double RRating;
    private Uri RWebURL;
    private PlaceInfo mPlace;



        @Override
    protected void onCreate(Bundle savedInstanceState) { // @Nullable  add before bundle?
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        /**SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
         .findFragmentById(R.id.map);
         mapFragment.getMapAsync(this);**/

       // mSearchText = (TextView) findViewById(R.id.input_search);

        mAutocompleteSupportFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        // Initialize Places.
        //client library
        Places.initialize(getApplicationContext(), API_KEY);
        // Create a new Places client instance.
        PlacesClient placesClient = Places.createClient(this);


        //filter to only the uk
        mAutocompleteSupportFragment.setCountry("UK");
        // Specify the types of place data to return.
        mAutocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG));

        // Set up a PlaceSelectionListener to handle the response.
        mAutocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.

                Log.d(TAG, "geoSearchLocate3: Adding shit");

                RLatLngLoc = place.getLatLng();
                Rname = place.getName();
                Raddr = place.getAddress();
                RID =  place.getId();
                RRating = place.getRating();
                RphnNumb = place.getPhoneNumber();
                RWebURL = place.getWebsiteUri();

                Log.d(TAG, "geoSearchLocate3: Added shit");

                Rboth = Rname + ", " + Raddr;
                // geoSearchLocate(); Values below:

                geoSearchLocate();



                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.


                //Toast.makeText(this, "" + status.toString(), Toast.LENGTH_SHORT).show();
                Log.i(TAG, "An error occurred: " + status);
            }
        });


        //gps icon
        mGPS = (ImageView) findViewById(R.id.ic_gps);

        //info icon
         mInfo = (ImageView) findViewById(R.id.place_Info);

         //maps nerby icon
         mNerby = (ImageView) findViewById(R.id.nerby_Places);

        getLocationPermission();


    }


    private void initSearch(){
        Log.d(TAG, "initSearch: Initializing search method");


        // override return key that will search therefore no onclick listener


        mGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked gps location");
                getDeviceLocation();
            }
        });

        mInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked Place Info");

                    if(mark.isInfoWindowShown()){
                        mark.hideInfoWindow();
                    }else{
                        Log.d(TAG, "onClick: clicked Place Info");

                        mark.showInfoWindow();
                    }

            }
        });

    /*    mNerby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int PLACE_PICKER_REQUEST = 1;


                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
            }
        });*/
    }


    private void geoSearchLocate(){

        Log.d(TAG, "geoSearchLocate: locating");


        Geocoder geoCoder = new Geocoder(Maps.this);

        List<Address> listAdd = new ArrayList<>();

        Log.d(TAG, "geoSearchLocateME: place name ME: " + Rname + "  " + Raddr + "  " + Rboth  );

        try{

            listAdd = geoCoder.getFromLocationName(Rboth, 1);

        }catch(IOException e){
            Log.e(TAG, "geoSearchLocateME: IOException: "+ e.getMessage() );
        }

        Log.d(TAG, "geoSearchLocateME: ListAdd: " + listAdd);
        if(listAdd.size()>0) {
            Address address = listAdd.get(0);

            Log.d(TAG, "geoSearchLocateME: Found Location: " + address.toString());

            String RTitle = Rname +address.getAddressLine(0) ;

            moveScreen(RLatLngLoc, DEFAULT_ZOOM, Rname);

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
                            moveScreen(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), DEFAULT_ZOOM, "My Location");


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
            initSearch();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {


                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);



        }
    }


    private void moveScreen(LatLng latlng, float zoom, String title){

        Log.e(TAG, "moveScreen: moving the screen to : lat:" + latlng.latitude + " lng: " + latlng.longitude ); // just so we know where is is moving the camera to
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,zoom));

        mMap.setInfoWindowAdapter(new CustomInfoMapAdapter(Maps.this));

        if(!title.equals("My Location")){

           String TxtBox = "Address: " + Raddr + "\n" +
                    "Phone Number: " + RphnNumb + "\n"
                    + "Rating: " + RRating + "\n" +
                    "Website URL: " + RWebURL + "\n";


            MarkerOptions options = new MarkerOptions().position(latlng).title(title).snippet(TxtBox);
            mark = mMap.addMarker(options);

        }


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









