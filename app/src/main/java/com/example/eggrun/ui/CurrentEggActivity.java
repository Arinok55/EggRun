package com.example.eggrun.ui;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.example.eggrun.classes.Player;

public class CurrentEggActivity extends SingleFragmentActivity {
    private static final String TAG = "CurrentEggActivity";

    @Override
    protected Fragment createFragment() {
        return new CurrentEggFragment();
    }
}
