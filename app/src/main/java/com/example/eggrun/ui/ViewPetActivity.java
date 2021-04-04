package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import com.example.eggrun.classes.Player;

public class ViewPetActivity extends SingleFragmentActivity{
    private static final String TAG = "ViewPetActivity";

    @Override
    protected Fragment createFragment(){
        Player player = (Player) getIntent().getSerializableExtra("player");
        int pos = (int) getIntent().getSerializableExtra("position");
        return new ViewPetFragment(player.getPetList().get(pos));
    }
}
