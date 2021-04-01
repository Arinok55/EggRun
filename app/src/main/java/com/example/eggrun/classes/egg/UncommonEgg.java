package com.example.eggrun.classes.egg;

import androidx.annotation.NonNull;

import com.example.eggrun.R;
import com.example.eggrun.classes.RunSession;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class UncommonEgg implements Egg, Serializable {
    private ArrayList<RunSession> mRunSessionList;
    private double mDistanceToHatch = 3.0;
    private int mImage = R.drawable.uncommon_egg_image;

    public UncommonEgg(){
        mRunSessionList = new ArrayList<>();
    }

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
    public boolean canHatch(){
        return mDistanceToHatch == 0;
    }

    @Override
    public int getImageId(){
        return mImage;
    }

    @Override
    @NonNull
    public String toString(){
        return "UNCOMMON";
    }
}

