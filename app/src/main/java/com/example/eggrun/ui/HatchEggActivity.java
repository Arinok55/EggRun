package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import com.example.eggrun.classes.Bus;
import com.example.eggrun.classes.Player;
import com.example.eggrun.classes.egg.Egg;
import com.example.eggrun.classes.pet.Pet;
import com.example.eggrun.classes.pet.PetFactory;

public class HatchEggActivity extends SingleFragmentActivity {
    private static final String TAG = "HatchEggActivity";

    @Override
    protected Fragment createFragment() {
        Bus bus = Bus.getInstance();
        Egg egg = bus.getPlayer().getEggList().remove((int) getIntent().getSerializableExtra("position"));
        PetFactory petFactory = new PetFactory();
        Pet mPet = petFactory.createPet(egg);

        bus.getPlayer().addPet(mPet);
        bus.getPlayer().saveData();

        return new HatchEggFragment(mPet);
    }
}
