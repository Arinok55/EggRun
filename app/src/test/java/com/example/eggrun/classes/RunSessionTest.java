package com.example.eggrun.classes;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class RunSessionTest {




    @Test
    public void getDistance() {
        RunSession runSession = new RunSession(1,100);
        assertEquals(1.0,runSession.getDistance(),0);
    }

    @Test
    public void getDate() {
        RunSession runSession = new RunSession(1,100);
        assertEquals(Calendar.getInstance().getTime(),runSession.getDate());
    }

    @Test
    public void getTimeRan() {
        RunSession runSession = new RunSession(1,100);
        assertEquals(100,runSession.getTimeRan(),0);
    }

    @Test
    public void getAvgSpeed() {
        RunSession runSession = new RunSession(1,100);
        assertEquals(1.0/100.0,runSession.getAvgSpeed(),0);
    }
}