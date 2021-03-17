package com.example.eggrun.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.eggrun.R;
import com.example.eggrun.classes.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;


public class MainActivityFragment extends Fragment {
    private File mGameDirectory;
    private String mGameDirectoryFileName = "EggRunDirectory";

    private Player mPlayer;
    private File mPlayerDirectory;
    private String mPlayerDirectoryFileName = "PlayerDirectory";

    private static final String TAG = "MainActivityFragment";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        fillDirectory();
        return inflater.inflate(R.layout.fragment_main_activity, container, false);
    }

    private void fillDirectory(){
        Context context = getContext().getApplicationContext();
        mGameDirectory = new File(context.getFilesDir() ,mGameDirectoryFileName);
        if (!mGameDirectory.isDirectory()) {
            Log.d(TAG, "Creating Directory");
            if (mGameDirectory.mkdir()){ Log.d(TAG, "Success");}
            else{Log.d(TAG, "Failed");}
        }

        File GameFiles[] = mGameDirectory.listFiles();
        if (GameFiles.length == 0){
            // Create first player object
            Activity activity = getActivity();
            startActivity(new Intent(activity, NewAccountActivity.class));
            Log.d(TAG, "Opening NewAccountActivity");
        }
        else {
            Log.d(TAG, "Starting without needing Account");
            Log.d(TAG, "Number of accounts: " + GameFiles.length);
            for (int i = 0; i < GameFiles.length; i++){
                try{
                    ObjectInputStream input = new ObjectInputStream(new FileInputStream(GameFiles[i]));
                    Player player = (Player) input.readObject();
                    String name = player.getName();
                    Log.d(TAG, "Player name: " + name);
                }
                catch(IOException | ClassNotFoundException e){e.printStackTrace();}
            }
        }
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
