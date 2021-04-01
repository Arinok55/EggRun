package com.example.eggrun.ui;

import androidx.fragment.app.Fragment;

import com.example.eggrun.classes.Player;
import com.example.eggrun.classes.egg.Egg;
import com.example.eggrun.classes.pet.Pet;
import com.example.eggrun.classes.pet.PetFactory;

public class HatchEggActivity extends SingleFragmentActivity {
    private static final String TAG = "HatchEggActivity";

    @Override
    protected Fragment createFragment() {
        Player mPlayer = (Player) getIntent().getSerializableExtra("player");

        Egg egg = mPlayer.getEggList().remove((int) getIntent().getSerializableExtra("position"));
        PetFactory petFactory = new PetFactory();
        Pet mPet = petFactory.createPet(egg);

        mPlayer.addPet(mPet);
        mPlayer.saveData();

        return new HatchEggFragment(mPlayer, mPet);
    }
}
