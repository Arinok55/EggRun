package com.example.eggrun.classes.egg;

import androidx.annotation.NonNull;

import com.example.eggrun.classes.RunSession;
import com.example.eggrun.classes.egg.Egg;

import java.util.List;

public class LegendaryEgg implements Egg {
    private List<RunSession> RunSessionList = null;
    private double distanceToHatch = 10;

    @Override
    public void addRunSession(RunSession runSession){
        RunSessionList.add(runSession);
        distanceToHatch -= runSession.getDistance();
        if (distanceToHatch < 0){
            distanceToHatch = 0;
        }
    }

    @Override
    public List<RunSession> getRunSessionList(){ return RunSessionList; }

    @Override
    public double getDistanceToHatch(){ return distanceToHatch; }

    @Override
    @NonNull
    public String toString(){ return "LEGENDARY"; }
}

