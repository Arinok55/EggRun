package com.example.eggrun.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.eggrun.R;

import org.jetbrains.annotations.NotNull;

import java.io.File;

public class SettingsFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "SettingsFragment";
    private File mAppDirectory;

    public SettingsFragment(File directory) {
        mAppDirectory = directory;
    }

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
        Button cancelButton = view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();

        if (viewId == R.id.new_account_button) {
            Log.d(TAG, "Opening NewAccountActivity");
            createDirectoryActivity(NewAccountActivity.class);
        }
        else if (viewId == R.id.login_button){
            Log.d(TAG, "Opening ChangeAccountActivity");
            createDirectoryActivity(ChangeAccountActivity.class);
        }
        else if (viewId == R.id.delete_account_button){
            Log.d(TAG, "Opening DeleteAccountActivity");
            createDirectoryActivity(DeleteAccountActivity.class);
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

    private void createDirectoryActivity(Class<?> classActivity){
        Intent intent = new Intent(getActivity(), classActivity);
        intent.putExtra("directory", mAppDirectory);
        startActivity(intent);
    }
}
