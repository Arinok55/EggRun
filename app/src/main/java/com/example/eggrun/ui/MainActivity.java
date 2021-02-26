package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

public class MainActivity extends SingleFragmentActivity {
    private MainActivityFragment mainActivityFragment;

    @Override
    protected Fragment createFragment() {
        if (mainActivityFragment == null){
            mainActivityFragment = new MainActivityFragment();
        }
        return mainActivityFragment;
    }
}
