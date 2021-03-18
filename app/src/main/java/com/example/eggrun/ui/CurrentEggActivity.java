package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import com.example.eggrun.classes.Player;

public class CurrentEggActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new CurrentEggFragment((Player) getIntent().getSerializableExtra("player"));
    }
}
