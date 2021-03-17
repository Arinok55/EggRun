package com.example.eggrun.classes.pet;

import android.media.Image;

import com.example.eggrun.classes.RunSession;
import com.example.eggrun.classes.egg.Egg;

import java.io.Serializable;
import java.util.List;

public class LegendaryPet implements Pet, Serializable {
    private Image mPetImage;
    private List<RunSession> mRunSessionList;

    public LegendaryPet(Egg egg){
        mRunSessionList = egg.getRunSessionList();
    }

    @Override
    public List<RunSession> getRunSessionList(){
        return mRunSessionList;
    }

    @Override
    public Image getImage(){
        return mPetImage;
    }
}
