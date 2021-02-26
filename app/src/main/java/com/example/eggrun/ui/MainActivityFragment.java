package com.example.eggrun.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.eggrun.R;

public class MainActivityFragment extends Fragment {
    private static final String TAG = "MainActivityFragment";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        return inflater.inflate(R.layout.fragment_main_activity, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart()");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }
}
