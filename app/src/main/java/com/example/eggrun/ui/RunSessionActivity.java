package com.example.eggrun.ui;

import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.eggrun.R;
import com.example.eggrun.classes.Player;
import com.example.eggrun.classes.egg.EggFactory;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;

public class RunSessionActivity extends SingleFragmentActivity implements OnMapReadyCallback {
    private static final String TAG = "RunSessionActivity";
    private RunSessionFragment runSessionFragment;

    private Location previousLocation;
    private Location currentLocation;
    private Marker currentMarker;

    private float totalDistance;
    private float[] distance = new float[1];


    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;


    //used to identify when request for permission was done
    private int LOCATION_REQUEST_CODE = 10001;

    LocationCallback locationCallback = new LocationCallback() {
        //this where you get the results of the location requests, gets new location every 4 seconds
        @Override
        public void onLocationResult(@NotNull LocationResult locationResult) {
            for (Location location : locationResult.getLocations()) {
                previousLocation = currentLocation;
                currentLocation = location;
                calculateDistance();
                if(runSessionFragment != null){
                    runSessionFragment.setDistance(totalDistance/1609);
                }
                SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                assert supportMapFragment != null;
                //calls onMapReady basically
                supportMapFragment.getMapAsync(RunSessionActivity.this);
            }
        }
    };

    @Override
    protected Fragment createFragment() {
        if (runSessionFragment == null) {
            int pos = (int) getIntent().getSerializableExtra("position");
            runSessionFragment = new RunSessionFragment(pos);
        }
        return runSessionFragment;
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_session);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        //request updates for the specified time, here updated every 2 seconds
        locationRequest.setInterval(2000);
        //the minimum allowed time your allowed to get updates, in other words you can't get get updates faster than 0.5 seconds here
        locationRequest.setFastestInterval(500);
        //might change to lower priority to save battery later
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        totalDistance = 0;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //we have setting permission so start locationUpdate
            checkSettingsAndStartLocationUpdates();
        } else {
            askLocationPermission();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopLocationUpdates();
    }

    private void checkSettingsAndStartLocationUpdates() {
        LocationSettingsRequest request = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest).build();
        SettingsClient client = LocationServices.getSettingsClient(this);

        Task<LocationSettingsResponse> locationSettingsResponseTask = client.checkLocationSettings(request);
        locationSettingsResponseTask.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                //Settings of device are satisfied and we can start location updates
                startLocationUpdates();
            }
        });
        locationSettingsResponseTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    ResolvableApiException apiException = (ResolvableApiException) e;
                    try {
                        apiException.startResolutionForResult(RunSessionActivity.this, 1001);
                    } catch (IntentSender.SendIntentException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }


    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    private void askLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        }
    }


    //this is where all the map drawing and camera is done
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //remove previous marker
        if(currentMarker != null){
            currentMarker.remove();
        }
        if(googleMap.getMapType() != GoogleMap.MAP_TYPE_HYBRID){
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        }
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions eggMarker = new MarkerOptions().position(latLng).title("You").icon(BitmapDescriptorFactory.fromBitmap(createMarker()));
        //googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        //this determines how much to zoom in and where
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));
        //adds new marker
        currentMarker = googleMap.addMarker(eggMarker);
    }

    public Bitmap createMarker(){
        int height = 150;
        int width = 150;
        BitmapDrawable eggImage = (BitmapDrawable)getResources().getDrawable(R.drawable.egg_marker);
        Bitmap eggBitMap = eggImage.getBitmap();
        return Bitmap.createScaledBitmap(eggBitMap, width, height, false);
    }

    public void calculateDistance(){
        if(previousLocation != null){
            double startLat = previousLocation.getLatitude();
            double startLng = previousLocation.getLongitude();
            double endLat = currentLocation.getLatitude();
            double endLng = currentLocation.getLongitude();
            Location.distanceBetween(startLat,startLng,endLat,endLng,distance);
            totalDistance += distance[0];
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                checkSettingsAndStartLocationUpdates();
            }
        }
    }


}

