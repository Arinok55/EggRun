package com.example.eggrun.ui;


import androidx.fragment.app.Fragment;

public class CurrentEggActivity extends SingleFragmentActivity {
    private static final String TAG = "CurrentEggActivity";

    @Override
    protected Fragment createFragment() {
        return new CurrentEggFragment();
    }
}
