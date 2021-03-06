package com.example.eggrun.classes.pet;

import android.media.Image;

import com.example.eggrun.classes.RunSession;

import java.util.List;

public interface Pet {
    void setRunSessionList(List<RunSession> runSessionList);
    List<RunSession> getRunSessionList();
    Image getImage();
}
