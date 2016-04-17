package com.example.domoa.uncommonhack;

import com.android.volley.*;
import com.android.volley.toolbox.*;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.LocationSource;

import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MapsActivity extends AppCompatActivity implements ConnectionCallbacks, OnConnectionFailedListener {

    @Bind(R.id.textView) TextView textView;
    private GoogleMap mMap;
    private Location myLocation;
    private UiSettings myUiSettings;
    private GoogleApiClient mGoogleApiClient;
    private String latitude;
    private String longitude;
//    final TextView mTextView = (TextView) findViewById(R.id.text);
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

    //myUiSettings.setMyLocationButtonEnabled(true);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            }
        queue = Volley.newRequestQueue(this);
        super.onCreate(savedInstanceState);
//        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
//        if (mLastLocation != null){
//            longitude = String.valueOf(mLastLocation.getLatitude());
//            latitude = String.valueOf(mLastLocation.getLongitude());
//            TextView t = (TextView)findViewById(R.id.text);
//            t.setText(longitude+" "+latitude);
//        }
        setContentView(R.layout.layout);
        //Obtain the SupportMapFragment and get notified when the map is ready to be used.




    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    public void onConnected(Bundle connectionHint){
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null){
            latitude = String.valueOf(mLastLocation.getLatitude());
            longitude = String.valueOf(mLastLocation.getLongitude());
            TextView t = (TextView)findViewById(R.id.text);
            //t.setText(longitude+" "+latitude);
        }
//        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        // Instantiate the RequestQueue.

        final String url = "https://maps.googleapis.com/maps/api/directions/json?mode=walking&origin="+latitude+","+longitude+"&destination=2217+N.+Seminary&key=AIzaSyAWfJogZXwrMc2Yd4UYGQzJ7CTId5Y5oxE";
        final TextView t = (TextView)findViewById(R.id.text);
//        if (mLastLocation != null){
//            String longitude = String.valueOf(mLastLocation.getLatitude());
//            String latitude = String.valueOf(mLastLocation.getLongitude());
//            //t.setText(longitude+" "+latitude);
//        }
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try {
                            JSONObject jsonObj = new JSONObject(response);
//                            String text = "Getting Routes...";
                            JSONArray routes = jsonObj.getJSONArray("routes");
//                            text += "\nGetting lets...";
                            JSONArray legs = routes.getJSONObject(0).getJSONArray("legs");
//                            text += "\nGetting steps...";
                            JSONArray steps = legs.getJSONObject(0).getJSONArray("steps");
                            t.setText(steps.toString());
//                            t.setText(routes.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            t.setText(url);
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                t.setText("That didn't work!");
            }
        });
        queue.add(stringRequest);
    }
    private class Move {
        
        public Move(String latitude, String longitude, String maneuver) {

        }
    }

    private ArrayList<Move>(JSONArray steps) {

    }

    public void onConnectionSuspended(int clause){
        Log.d("Connection suspended", "Connection suspended");
    }
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d("Connection failed", "Connection failed");
    }
    //}

            //mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            //    @Override
             //   public void onMyLocationChange(Location l) {
             //       myLocation = l;

             //   }
            //});

        //}

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera. In this case,
         * we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to install
         * it inside the SupportMapFragment. This method will only be triggered once the user has
         * installed Google Play services and returned to the app.
         */

        public void onMapReady(GoogleMap googleMap) {





            // Add a marker in Sydne   y and move the camera
            //LatLng sydney = new LatLng(-34, 151);
            //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
}
