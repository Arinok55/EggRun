package com.example.eggrun.classes.egg;

import androidx.annotation.NonNull;

import com.example.eggrun.classes.RunSession;
import com.example.eggrun.classes.egg.Egg;

import java.util.List;

public class UncommonEgg implements Egg {
    private List<RunSession> RunSessionList = null;
    private double mDistanceToHatch = 3;

    @Override
    public void addRunSession(RunSession runSession){
        RunSessionList.add(runSession);
        mDistanceToHatch -= runSession.getDistance();
        if (mDistanceToHatch < 0){
            mDistanceToHatch = 0;
        }
    }

    @Override
    public List<RunSession> getRunSessionList(){ return RunSessionList; }

    @Override
    public double DistanceToHatch(){ return mDistanceToHatch; }

    @Override
    @NonNull
    public String toString(){ return "UNCOMMON"; }
}

