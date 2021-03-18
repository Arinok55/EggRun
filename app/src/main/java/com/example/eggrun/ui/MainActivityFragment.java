package com.example.eggrun.ui;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.example.eggrun.R;
import com.example.eggrun.classes.Player;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainActivityFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "MainActivityFragment";
    private File mAppDirectory;
    private Player mPrimaryPlayer;

    public MainActivityFragment(File appDirectory) {
        mAppDirectory = appDirectory;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        try {
            checkPrimaryPlayer();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        View view = inflater.inflate(R.layout.fragment_main_activity, container, false);
        Button hatchNewEggButton = view.findViewById(R.id.hatchButton);
        hatchNewEggButton.setOnClickListener(this);
        Button currentEggButton = view.findViewById(R.id.currentButton);
        currentEggButton.setOnClickListener(this);
        Button petsButton = view.findViewById(R.id.petsButton);
        petsButton.setOnClickListener(this);
        Button optionsButton = view.findViewById(R.id.optionsButton);
        optionsButton.setOnClickListener(this);

        return view;
    }

    private boolean getPrimaryPlayer() throws IOException, ClassNotFoundException {
        ObjectInputStream input;
        Player player;
        File[] appFiles = mAppDirectory.listFiles();

        for (File appFile : appFiles) {
            input = new ObjectInputStream(new FileInputStream(appFile));
            player = (Player) input.readObject();
            if (player.isPrimary()) {
                mPrimaryPlayer = player;
                mPrimaryPlayer.setDirectory(mAppDirectory);
                input.close();
                return true;
            }
            input.close();
        }
        return false;
    }

    public void onClick(View view) {
        final int viewId = view.getId();
        try {
            checkPrimaryPlayer();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Activity activity = getActivity();
        if(viewId == R.id.hatchButton){
            Intent intent = new Intent(activity, AddEggActivity.class);
            intent.putExtra("player", mPrimaryPlayer);
            startActivity(intent);
            Toast.makeText(activity.getApplicationContext(), "Pick an egg to add to the collection", Toast.LENGTH_SHORT).show();
        }
        else if (viewId == R.id.currentButton){
            Intent intent = new Intent(activity, CurrentEggActivity.class);
            intent.putExtra("player", mPrimaryPlayer);
            startActivity(intent);
        }
        else if (viewId == R.id.petsButton){
            Toast.makeText(activity.getApplicationContext(), "Pets button not implemented", Toast.LENGTH_SHORT).show();
        }
        else if (viewId == R.id.optionsButton){
            Toast.makeText(activity.getApplicationContext(), "Options button not implemented", Toast.LENGTH_SHORT).show();
        }
        else{
            Log.d(TAG, "Error: Invalid button click");
        }
    }

    public void checkPrimaryPlayer() throws IOException, ClassNotFoundException {
        if (mPrimaryPlayer == null && !getPrimaryPlayer()) {
            Log.d(TAG, "Opening new account activity");
            Activity activity = getActivity();
            Intent intent = new Intent(activity, NewAccountActivity.class);
            intent.putExtra("directory", mAppDirectory);
            startActivity(intent);
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart()");
        try {
            getPrimaryPlayer();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
