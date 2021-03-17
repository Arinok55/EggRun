package com.example.eggrun.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.eggrun.R;

public class MainActivityFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "MainActivityFragment";
    private Button mHatchEggButton, mPetsButton, mCollectionButton, mOptionsButton;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        View v = inflater.inflate(R.layout.fragment_main_activity, container, false);
        mHatchEggButton = v.findViewById(R.id.hatchButton);
        mHatchEggButton.setOnClickListener(this);
        return v;
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

    public void onClick(View v) {
        final int viewId = v.getId();

        if(viewId == R.id.hatchButton){
            //start Egg selection activity
            Intent intent = new Intent(MainActivityFragment.this.getActivity(), EggSelectionActivity.class);
            startActivity(intent);
        }
    }

    }
