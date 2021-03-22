package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import java.io.File;

public class SettingsActivity extends SingleFragmentActivity{
    private static final String TAG = "SettingsActivity";
    private SettingsFragment mSettingsFragment;
    private File mDirectory;

    @Override
    protected Fragment createFragment(){
        if (mSettingsFragment == null){
            mDirectory = (File) getIntent().getSerializableExtra("directory");
            mSettingsFragment = new SettingsFragment(mDirectory);
        }
        return mSettingsFragment;
    }
}
