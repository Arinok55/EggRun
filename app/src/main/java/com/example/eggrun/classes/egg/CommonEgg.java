package com.example.eggrun.classes.egg;

import androidx.annotation.NonNull;

import com.example.eggrun.classes.RunSession;

import java.io.Serializable;
import java.util.List;


public class CommonEgg implements Egg, Serializable {
    private List<RunSession> mRunSessionList;
    private double mDistanceToHatch = 1;

    @Override
    public void addRunSession(RunSession runSession){
        mRunSessionList.add(runSession);
        mDistanceToHatch -= runSession.getDistance();
        if (mDistanceToHatch < 0){
            mDistanceToHatch = 0;
        }
    }

    @Override
    public List<RunSession> getRunSessionList(){
        return mRunSessionList;
    }

    @Override
    public double DistanceToHatch(){
        return mDistanceToHatch;
    }

    @Override
    @NonNull
    public String toString(){
        return "COMMON";
    }
}

