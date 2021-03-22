package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import java.io.File;

public class NewAccountActivity extends SingleFragmentActivity {
    private NewAccountFragment mNewAccountFragment;
    private File mDirectory;

    @Override
    protected Fragment createFragment(){
        if (mNewAccountFragment == null){
            mDirectory = (File) getIntent().getSerializableExtra("directory");
            mNewAccountFragment = new NewAccountFragment(mDirectory);
        }
        return mNewAccountFragment;
    }
}