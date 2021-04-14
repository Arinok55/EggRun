package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import java.io.File;

public class SettingsActivity extends SingleFragmentActivity{
    private static final String TAG = "SettingsActivity";

    @Override
    protected Fragment createFragment(){
        return new SettingsFragment();
    }
}
