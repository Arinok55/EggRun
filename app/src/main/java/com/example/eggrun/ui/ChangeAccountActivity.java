package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import java.io.File;

public class ChangeAccountActivity extends SingleFragmentActivity {
    private final String TAG = "ChangeAccountActivity";


    @Override
    protected Fragment createFragment(){
        return new ChangeAccountFragment();
    }
}
