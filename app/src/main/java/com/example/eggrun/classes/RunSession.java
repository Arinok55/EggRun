package com.example.eggrun.classes;

import java.sql.Time;

public class RunSession {
    private int distance = 0;
    private Time time = null;

    public RunSession(){}

    public int getDistance(){
        return distance;
    }

    public Time getTime(){
        return time;
    }
}
