package com.example.eggrun.classes.pet;

import android.media.Image;

import com.example.eggrun.classes.RunSession;

import java.util.List;

public class LegendaryPet implements Pet {
    private static Image PetImage = null;
    private List<RunSession> RunSessionList = null;

    @Override
    public void setRunSessionList(List<RunSession> runSessionList){
        RunSessionList = runSessionList;
    }

    @Override
    public List<RunSession> getRunSessionList(){
        return RunSessionList;
    }

    @Override
    public Image getImage(){
        return PetImage;
    }
}
