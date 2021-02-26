package com.example.eggrun;

import java.sql.Time;

public class RunSession {
    private int distance = 0;
    private Time time = null;
    private static RunSession runSession = null;

    private RunSession() {/* Singleton model */}

    public static RunSession RunSessionCreate(int distance, Time time){
        if (runSession == null){
            runSession = new RunSession();
            runSession.time = time;
            runSession.distance = distance;
        }
        return runSession;
    }

    public int getDistance(){
        return distance;
    }

    public Time getTime(){
        return time;
    }
}
