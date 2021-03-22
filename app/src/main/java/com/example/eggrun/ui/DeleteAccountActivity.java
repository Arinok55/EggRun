package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import java.io.File;

public class DeleteAccountActivity extends SingleFragmentActivity {
    private static final String TAG = "DeleteAccountActivity";
    private DeleteAccountFragment mDeleteAccountFragment;
    private File mDirectory;

    @Override
    protected Fragment createFragment(){
        if (mDeleteAccountFragment == null){
            mDirectory = (File) getIntent().getSerializableExtra("directory");
            mDeleteAccountFragment = new DeleteAccountFragment(mDirectory);
        }
        return mDeleteAccountFragment;
    }
}