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
    private String mName;
    private String mPassword;
    private File mDirectory;
    private boolean mPrimaryProfile = true;

    private ArrayList<Egg> mEggList;
    private ArrayList<Pet> mPetList;

    public Player(String name, String password) {
        mName = name;
        mPassword = password;
        mEggList = new ArrayList<>();
        mPetList = new ArrayList<>();
        saveData();
    }

    public String getName(){
        return mName;
    }

    public String getPassword(){
        return mPassword;
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

    public boolean setPrimary() {
        mPrimaryProfile = true;
        return saveData();
    }

    public boolean removePrimary() {
        mPrimaryProfile = false;
        return saveData();
    }

    public boolean isPrimary() {
        return mPrimaryProfile;
    }

    public void setDirectory(File directory){
        mDirectory = directory;
    }

    public boolean saveData() {
        if(mDirectory != null && mDirectory.isDirectory()) {
            try {
                ObjectOutputStream output;
                output = new ObjectOutputStream(new FileOutputStream(new File(mDirectory, mName)));
                output.writeObject(this);
                output.close();
                return true;
            }
            catch (IOException e){
                return false;
            }
        }
        else{
            return false;
        }
    }
}
