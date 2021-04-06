package com.example.eggrun.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.eggrun.R;
import com.example.eggrun.classes.Player;
import com.example.eggrun.classes.pet.Pet;

import org.jetbrains.annotations.NotNull;

public class HatchEggFragment extends Fragment {
    private static final String TAG = "HatchEggFragment";
    private Pet mPet;

    private boolean mIsActive = true;
    private final int mScreenTime = 3000;
    private final int mTimeIncrement = 100;
    private final int mSleepTime = 100;

    public HatchEggFragment(Pet pet){
        mPet = pet;
    }

    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        View view = inflater.inflate(R.layout.fragment_hatch_egg, container, false);
        ImageView imageView = view.findViewById(R.id.pet_image_view);
        imageView.setImageResource(mPet.getImage());
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int elapsedTime = 0;
                    while (elapsedTime < mScreenTime) {
                        sleep(mSleepTime);
                        if (mIsActive) elapsedTime = elapsedTime + mTimeIncrement;
                    }
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Activity activity = requireActivity();
                    activity.finish();
                    Intent intent = new Intent(getContext(), CurrentEggActivity.class);
                    startActivity(intent);
                }
            }
        };
        splashThread.start();
    }
}
