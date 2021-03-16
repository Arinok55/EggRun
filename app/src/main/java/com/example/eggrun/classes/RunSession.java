package com.example.eggrun.classes;

import com.example.eggrun.classes.egg.Egg;

import java.sql.Time;

public class RunSession {
    private int distance = 0;
    private Time time = null;
    private Egg currentEgg;

    public RunSession(Egg egg){
        currentEgg = egg;
    }

    public int getDistance(){
        return distance;
    }

    public Time getTime(){
        return time;
    }
}
