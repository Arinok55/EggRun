package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import com.example.eggrun.R;

public class NewAccountActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new NewAccountFragment();
    }
}