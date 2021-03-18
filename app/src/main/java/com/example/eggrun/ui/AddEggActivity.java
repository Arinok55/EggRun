package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import com.example.eggrun.classes.Player;

public class AddEggActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new AddEggFragment((Player) getIntent().getSerializableExtra("player"));
    }
}
