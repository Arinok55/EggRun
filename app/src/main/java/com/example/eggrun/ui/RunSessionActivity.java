package com.example.eggrun.ui;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.eggrun.R;
import com.example.eggrun.classes.BackgroundLocationService;
import com.example.eggrun.classes.LocationServiceUtil;
import com.example.eggrun.classes.Player;
import com.example.eggrun.classes.RunSession;
import com.example.eggrun.classes.SendLocationToActivity;
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
import android.view.View;
import android.widget.Button;
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
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.PreferenceChangeEvent;

public class RunSessionActivity extends SingleFragmentActivity implements OnMapReadyCallback {
    private static final String TAG = "RunSessionActivity";

    BackgroundLocationService backgroundLocationService = null;
    boolean mBound = false;

    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BackgroundLocationService.LocalBinder binder = (BackgroundLocationService.LocalBinder)service;
            backgroundLocationService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            backgroundLocationService = null;
            mBound = false;
        }
    };

    private RunSessionFragment runSessionFragment;

    private Location previousLocation;
    private Location currentLocation;
    private Marker currentMarker;

    private float totalDistance;
    private float[] distance = new float[1];

    private int pos;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;


    //used to identify when request for permission was done
    private int LOCATION_REQUEST_CODE = 10001;

    @Override
    protected Fragment createFragment() {
        if (runSessionFragment == null) {
            pos = (int) getIntent().getSerializableExtra("position");
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

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        //Manifest.permission.ACCESS_BACKGROUND_LOCATION
                     Manifest.permission.ACCESS_COARSE_LOCATION
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                Log.d(TAG,"permissions allowed");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //check to see if a BackgroundService is already running, if its 0 that means no service was started before
                        if(backgroundLocationService.getSeconds() == 0){
                            backgroundLocationService.requestLocationUpdates();
                            backgroundLocationService.setEggPostion(pos);
                        }
                        runSessionFragment.setBackgroundLocationService(backgroundLocationService);
                        backgroundLocationService.setRunSessionFragment(runSessionFragment);

                    }
                }, 2000);
                bindService(new Intent(RunSessionActivity.this,
                                BackgroundLocationService.class),
                        mServiceConnection,
                        Context.BIND_AUTO_CREATE);
            }
            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
            }
        }).check();
    }



    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        if(mBound){
            unbindService(mServiceConnection);
            mBound = false;
        }
        Log.d(TAG,"Stopping Run Activity");
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("RunSessionActive","hi");
        Log.d(TAG,"onSaveInstanceState");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"Destroying");
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

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onListenLocation(SendLocationToActivity event){
        if(event != null){
            //call change map here
            currentLocation = event.getLocation();
            SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            assert supportMapFragment != null;
            //calls onMapReady basically
            supportMapFragment.getMapAsync(RunSessionActivity.this);
        }
    }

}

