package com.example.eggrun.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.eggrun.R;

public class EggSelectionActivity extends SingleFragmentActivity {
    private EggSelectionFragment eggSelectionFragment;

    @Override
    protected Fragment createFragment() {
        if (eggSelectionFragment == null){
            eggSelectionFragment = new EggSelectionFragment();
        }
        return eggSelectionFragment;
    }

    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg_selection);
    }

}
