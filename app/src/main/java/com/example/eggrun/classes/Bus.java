package com.example.eggrun.classes;

public class Bus {
    private static Bus instance = new Bus();
    private Player mPlayer;

    private Bus(){}

    public static Bus getInstance(){
        return instance;
    }

    public Player getPlayer(){
        return mPlayer;
    }

    public void setPlayer(Player player){
        mPlayer = player;
    }

    public boolean hasPlayer(){
        return mPlayer != null;
    }

    public void removePlayer(){
        mPlayer = null;
    }
}
