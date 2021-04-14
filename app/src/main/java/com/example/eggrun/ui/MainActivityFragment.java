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
import com.example.eggrun.classes.BackgroundLocationService;
import com.example.eggrun.classes.Bus;
import com.example.eggrun.classes.Player;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainActivityFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "MainActivityFragment";
    private Bus mBus = Bus.getInstance();

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
                Intent intent = new Intent(getActivity(), AddEggActivity.class);
                startActivity(intent);
            } else if (viewId == R.id.currentButton) {
                Log.d(TAG, "opening CurrentEggActivity");
                Intent intent = new Intent(getActivity(), CurrentEggActivity.class);
                startActivity(intent);
            } else if (viewId == R.id.petsButton) {
                Intent intent = new Intent(getActivity(), PetActivity.class);
                startActivity(intent);
            } else if (viewId == R.id.optionsButton) {
                Log.d(TAG, "opening SettingsActivity");
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
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
        Log.d(TAG, "Service is " + BackgroundLocationService.getInstance());
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

    private void openNewAccount() throws IOException, ClassNotFoundException {
        if (!getPrimaryPlayer()) {
            Log.d(TAG, "Opening new account activity");
            Intent intent = new Intent(getActivity(), NewAccountActivity.class);
            startActivity(intent);
        }
    }

    private boolean getPrimaryPlayer() throws IOException, ClassNotFoundException {
        if (mBus.hasFile(mBus.getMainDirectory(), "primaryPlayer")) {
            File primaryPlayer = mBus.getFile(mBus.getMainDirectory(), "primaryPlayer");
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(primaryPlayer));
            String playerHash = (String) input.readObject();

            if (mBus.hasFile(mBus.getPlayerDirectory(), playerHash)){
                File player = mBus.getFile(mBus.getPlayerDirectory(), playerHash);
                ObjectInputStream inputPlayer = new ObjectInputStream(new FileInputStream(player));
                mBus.setPlayer((Player) inputPlayer.readObject());
                inputPlayer.close();
                input.close();
                return true;
            }
            input.close();
        }
        return false;
    }
}
