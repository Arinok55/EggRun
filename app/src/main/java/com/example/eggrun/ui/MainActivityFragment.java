package com.example.eggrun.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.example.eggrun.R;
import com.example.eggrun.classes.BackgroundLocationService;
import com.example.eggrun.classes.Bus;
import com.example.eggrun.classes.Player;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainActivityFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "MainActivityFragment";
    private final File mAppDirectory;
    private Bus mBus = Bus.getInstance();

    public MainActivityFragment(File appDirectory) {
        mAppDirectory = appDirectory;
    }

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");

        try {
            openNewAccount();
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

    public void onClick(View view) {
        final int viewId = view.getId();

        if (!mBus.hasPlayer()) {
            try {
                openNewAccount();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            if (viewId == R.id.hatchButton) {
                Log.d(TAG, "opening AddEggActivity");
                createNewActivity(AddEggActivity.class);
            } else if (viewId == R.id.currentButton) {
                Log.d(TAG, "opening CurrentEggActivity");
                createNewActivity(CurrentEggActivity.class);
            } else if (viewId == R.id.petsButton) {
                createNewActivity(PetActivity.class);
            } else if (viewId == R.id.optionsButton) {
                Log.d(TAG, "opening SettingsActivity");
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                intent.putExtra("directory", mAppDirectory);
                startActivity(intent);
            } else {
                Log.d(TAG, "Error: Invalid button click");
            }
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart()");
        Log.d(TAG, "Serivce is " + BackgroundLocationService.getInstance());
        try {
            getPrimaryPlayer();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(BackgroundLocationService.getInstance() != null){
            Intent intent = new Intent(this.getContext(), RunSessionActivity.class);
            intent.putExtra("position", BackgroundLocationService.getInstance().getEggPostion());
            requireActivity().finish();
            this.getContext().startActivity(intent);

        }
    }

    private boolean getPrimaryPlayer() throws IOException, ClassNotFoundException {
        ObjectInputStream input;
        Player player;
        File[] appFiles = mAppDirectory.listFiles();

        for (File appFile : appFiles) {
            input = new ObjectInputStream(new FileInputStream(appFile));
            player = (Player) input.readObject();
            if (player.isPrimary()) {
                mBus.setPlayer(player);
                mBus.getPlayer().setDirectory(mAppDirectory);
                input.close();
                return true;
            }
            input.close();
        }
        return false;
    }

    private void openNewAccount() throws IOException, ClassNotFoundException {
        if (!getPrimaryPlayer()) {
            Log.d(TAG, "Opening new account activity");
            Intent intent = new Intent(getActivity(), NewAccountActivity.class);
            intent.putExtra("directory", mAppDirectory);
            startActivity(intent);
        }
    }

    public void createNewActivity(Class<?> activity){
        Intent intent = new Intent(getActivity(), activity);
        startActivity(intent);
    }
}
