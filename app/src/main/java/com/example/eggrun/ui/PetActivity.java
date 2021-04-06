package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import com.example.eggrun.classes.Player;

public class PetActivity extends SingleFragmentActivity{
    private static final String TAG = "PetActivity";

    @Override
    protected Fragment createFragment(){
        return new PetFragment();
    }
}
