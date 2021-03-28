package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import com.example.eggrun.classes.Player;
import com.example.eggrun.classes.egg.Egg;

public class TEST_EGG_HATCH  extends SingleFragmentActivity{
    private static final String TAG = "TEST_EGG_HATCH";

    @Override
    protected Fragment createFragment() {
        return new TEST_EGG_HATCH_FRAGMENT((Egg) getIntent().getSerializableExtra("egg"));
    }
}
