package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import com.example.eggrun.classes.Bus;
import com.example.eggrun.classes.Player;

public class ViewPetActivity extends SingleFragmentActivity{
    private static final String TAG = "ViewPetActivity";

    @Override
    protected Fragment createFragment(){
        Bus bus = Bus.getInstance();
        int pos = (int) getIntent().getSerializableExtra("position");
        return new ViewPetFragment(bus.getPlayer().getPetList().get(pos));
    }
}
