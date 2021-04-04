package com.example.eggrun.classes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class RunSession implements Serializable {
    private final double mDistance;
    private final float mTimeRan; // in Seconds
    private final Calendar mCalendar;

    public RunSession(double distance, float timeRan){
        mDistance = distance;
        mTimeRan = timeRan;
        mCalendar = Calendar.getInstance();
    }

    public double getDistance(){
        return mDistance;
    }

    public Date getDate(){
        return mCalendar.getTime();
    }

    public float getTimeRan(){
        return mTimeRan;
    }

    public double getAvgSpeed(){
        return mDistance / mTimeRan;
    }
}
