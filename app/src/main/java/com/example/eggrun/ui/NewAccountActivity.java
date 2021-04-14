package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import java.io.File;

public class NewAccountActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new NewAccountFragment();
    }
}