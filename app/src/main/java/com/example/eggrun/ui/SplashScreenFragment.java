package com.example.eggrun.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eggrun.R;

public class SplashScreenFragment extends Fragment {
    private boolean mIsActive = true;
    private final int mSplashTime = 1000;
    private final int mTimeIncrement = 100;
    private final int mSleepTime = 100;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Thread for displaying the SplashScreen
        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int elapsedTime = 0;
                    while (elapsedTime < mSplashTime) {
                        sleep(mSleepTime);
                        if (mIsActive) elapsedTime = elapsedTime + mTimeIncrement;
                    }
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Activity activity = requireActivity();
                    activity.finish();
                    startActivity(new Intent("com.example.eggrun.main"));
                }
            }
        };
        splashThread.start();
    }
}