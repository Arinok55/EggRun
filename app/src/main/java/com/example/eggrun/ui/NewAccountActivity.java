package com.example.eggrun.ui;

import android.preference.PreferenceManager;

import androidx.fragment.app.Fragment;

import com.example.eggrun.R;

import java.io.File;

public class NewAccountActivity extends SingleFragmentActivity {
    private NewAccountFragment mNewAccountFragment;
    private File directory;

    @Override
    protected Fragment createFragment(){
        if (mNewAccountFragment == null){
            directory = (File) getIntent().getSerializableExtra("directory");
            mNewAccountFragment = new NewAccountFragment(directory);
        }
        return mNewAccountFragment;
    }

}