package com.example.eggrun.classes.egg;

import com.example.eggrun.classes.RunSession;

import java.io.Serializable;
import java.util.List;

public interface Egg {
    void addRunSession(RunSession runSession);
    List<RunSession> getRunSessionList();
    double DistanceToHatch();
    boolean canHatch();
    int getImageId();
}
