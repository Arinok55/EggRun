package com.example.eggrun.classes;

import java.io.Serializable;
import java.sql.Time;

public class RunSession implements Serializable {
    private final double mDistance;
    //in seconds
    private float mTimeRan;
    private Time mTime;

    public RunSession(double distance, float timeRan){
        mDistance = distance;
        mTimeRan = timeRan;
    }

    public double getDistance(){
        return mDistance;
    }

    public Time getTime(){
        return mTime;
    }

    public float getTimeRan(){
        return mTimeRan;
    }

}
