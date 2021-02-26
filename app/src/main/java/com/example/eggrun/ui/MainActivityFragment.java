package com.example.eggrun.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MainActivityFragment extends Fragment {
    private static final int ANDROID_TIMEOUT_BASE = 500;
    private static final int ANDROID_TIMEOUT_SEED = 2000;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(null, "onCreate()");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(null, "onStart()");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(null, "onResume()");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(null, "onPause()");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(null, "onStop()");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(null, "onDestroy()");
    }
}
