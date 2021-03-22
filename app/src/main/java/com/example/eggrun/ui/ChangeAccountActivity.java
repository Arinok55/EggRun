package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import java.io.File;

public class ChangeAccountActivity extends SingleFragmentActivity {
    private final String TAG = "ChangeAccountActivity";
    private ChangeAccountFragment mChangeAccountFragment;
    private File mDirectory;

    @Override
    protected Fragment createFragment(){
        if (mChangeAccountFragment == null){
            mDirectory = (File) getIntent().getSerializableExtra("directory");
            mChangeAccountFragment = new ChangeAccountFragment(mDirectory);
        }
        return mChangeAccountFragment;
    }
}
