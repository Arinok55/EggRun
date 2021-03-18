package com.example.eggrun.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;


import com.example.eggrun.R;
import com.example.eggrun.classes.Player;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainActivityFragment extends Fragment {
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
            if (mPrimaryPlayer == null && !getPrimaryPlayer()) {
                Log.d(TAG, "Opening new account activity");
                Activity activity = getActivity();
                Intent intent = new Intent(activity, NewAccountActivity.class);
                intent.putExtra("directory", mAppDirectory);
                startActivity(intent);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return inflater.inflate(R.layout.fragment_main_activity, container, false);
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
                return true;
            }
            input.close();
        }
        return false;
    }
}
