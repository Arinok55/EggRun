package com.example.eggrun.classes;

import com.example.eggrun.classes.egg.Egg;
import com.example.eggrun.classes.pet.Pet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private final String mName;
    private final String mFileName;
    private boolean darkMode = false;
    private final Bus bus = Bus.getInstance();

    private ArrayList<Egg> mEggList;
    private ArrayList<Pet> mPetList;

    public Player(String name, String fileName) {
        mName = name;
        mFileName = fileName;
        mEggList = new ArrayList<>();
        mPetList = new ArrayList<>();
    }

    public String getName(){
        return mName;
    }

    public ArrayList<Egg> getEggList(){
        return mEggList;
    }

    public boolean addEgg(Egg egg) {
        mEggList.add(egg);
        return saveData();
    }

    public boolean removeEgg(Egg egg) {
        mEggList.remove(egg);
        return saveData();
    }

    public ArrayList<Pet> getPetList(){
        return mPetList;
    }

    public boolean addPet(Pet pet) {
        mPetList.add(pet);
        return saveData();
    }


    public boolean saveData() {
        try {
            ObjectOutputStream output;
            output = new ObjectOutputStream(new FileOutputStream(new File(bus.getPlayerDirectory(), mFileName)));
            output.writeObject(this);
            output.close();
            return true;
        }
        catch (IOException e){
            return false;
        }
    }

    public void switchDarkMode(){
        darkMode = !darkMode;
        saveData();
    }

    public boolean isDarkModeActive(){
        return darkMode;
    }
}
