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

    public Player(String name, String password) throws IOException {
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

    public void addEgg(Egg egg) throws IOException {
        mEggList.add(egg);
        saveData();
    }

    public void removeEgg(Egg egg) throws IOException {
        mEggList.remove(egg);
        saveData();
    }

    public ArrayList<Pet> getPetList(){
        return mPetList;
    }

    public void addPet(Pet pet) throws IOException {
        mPetList.add(pet);
        saveData();
    }

    public void setPrimary() throws IOException {
        mPrimaryProfile = true;
        saveData();
    }

    public void removePrimary() throws IOException {
        mPrimaryProfile = false;
        saveData();
    }

    public boolean isPrimary() {
        return mPrimaryProfile;
    }

    public void setDirectory(File directory){
        mDirectory = directory;
    }

    private void saveData() throws IOException {
        if(mDirectory != null && mDirectory.isDirectory()){
            ObjectOutputStream output;
            output = new ObjectOutputStream(new FileOutputStream(new File(mDirectory, mName)));
            output.writeObject(this);
            output.close();
        }
    }
}
