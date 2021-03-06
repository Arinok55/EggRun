package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;
import com.example.eggrun.R;

import android.os.Bundle;

public class SplashScreenActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new SplashScreenFragment();
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }
}