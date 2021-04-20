package com.example.eggrun.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.eggrun.R;
import com.example.eggrun.classes.Bus;

import org.jetbrains.annotations.NotNull;

import java.io.File;

import static com.example.eggrun.R.drawable.egg_run_title;

public class SettingsFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "SettingsFragment";
    private Bus bus = Bus.getInstance();

    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        Button newAccountButton = view.findViewById(R.id.new_account_button);
        newAccountButton.setOnClickListener(this);
        Button changeAccountButton = view.findViewById(R.id.login_button);
        changeAccountButton.setOnClickListener(this);
        Button deleteAccountButton = view.findViewById(R.id.delete_account_button);
        deleteAccountButton.setOnClickListener(this);
        Button darkModeButton = view.findViewById(R.id.dark_mode_button);
        darkModeButton.setOnClickListener(this);
        Button cancelButton = view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(this);

        if (bus.isDarkModeActive()){
            view.getRootView().setBackgroundColor(Color.BLACK);
            ImageView title = view.findViewById(R.id.egg_Run_Title);
            title.setImageResource(R.drawable.egg_run_title_dark);
            darkModeButton.setText("Turn off dark mode");
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();

        if (viewId == R.id.new_account_button) {
            Log.d(TAG, "Opening NewAccountActivity");
            Intent intent = new Intent(getActivity(), NewAccountActivity.class);
            startActivity(intent);
        }
        else if (viewId == R.id.login_button){
            Log.d(TAG, "Opening ChangeAccountActivity");
            Intent intent = new Intent(getActivity(), ChangeAccountActivity.class);
            startActivity(intent);
        }
        else if (viewId == R.id.delete_account_button){
            Log.d(TAG, "Opening DeleteAccountActivity");
            Intent intent = new Intent(getActivity(), DeleteAccountActivity.class);
            startActivity(intent);
        }
        else if (viewId == R.id.dark_mode_button){
            Log.d(TAG, "Switching dark mode");
            bus.switchDarkMode();
            if (bus.isDarkModeActive()){
                view.getRootView().setBackgroundColor(Color.BLACK);
                ImageView title = view.getRootView().findViewById(R.id.egg_Run_Title);
                title.setImageResource(R.drawable.egg_run_title_dark);

                Button darkModeButton = view.getRootView().findViewById(R.id.dark_mode_button);
                darkModeButton.setText("Turn off dark mode");
            }
            else {
                view.getRootView().setBackgroundColor(Color.WHITE);
                ImageView title = view.getRootView().findViewById(R.id.egg_Run_Title);
                title.setImageResource(egg_run_title);

                Button darkModeButton = view.getRootView().findViewById(R.id.dark_mode_button);
                darkModeButton.setText("Turn on dark mode");
            }
        }
        else if (viewId == R.id.cancel_button){
            Log.d(TAG, "Closing SettingsActivity");
            Activity activity = getActivity();
            assert activity != null;
            activity.finish();
        }
        else{
            Log.d(TAG, "Error: Invalid button click");
        }
    }
}
