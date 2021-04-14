package com.example.eggrun.classes;

import java.io.File;
import java.io.Serializable;

public class Bus implements Serializable {
    private static final Bus instance = new Bus();
    private File mainDirectory;
    private File playerDirectory;
    private Player mPlayer = null;

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

    public boolean setDirectory(File directory){
        mainDirectory = directory;
        if (!mainDirectory.isDirectory())
        {
            mainDirectory.mkdir();
        }

        if (hasFile(mainDirectory, "playerDirectory")){
            playerDirectory = getFile(mainDirectory, "playerDirectory");
        }
        else {
            playerDirectory = new File(mainDirectory, "playerDirectory");
            playerDirectory.mkdir();
        }
        return true;
    }

    public File getMainDirectory(){
        return mainDirectory;
    }

    public File getPlayerDirectory(){
        return playerDirectory;
    }

    public boolean hasFile(File directory, String fileName){
        File[] files = directory.listFiles();
        for (File file : files){
            if (file.getName().equals(fileName)){
                return true;
            }
        }
        return false;
    }

    public File getFile(File directory, String fileName){
        File[] files = directory.listFiles();
        for (File file : files){
            if (file.getName().equals(fileName)){
                return file;
            }
        }
        return null;
    }
}
