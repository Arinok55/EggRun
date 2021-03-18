package com.example.eggrun.classes;

import com.example.eggrun.classes.egg.Egg;
import com.example.eggrun.classes.pet.Pet;

import java.io.Serializable;
import java.util.List;

public class Player implements Serializable {
    private String mName;
    private String mPassword;
    private String mFileName;
    private boolean mPrimaryProfile = true;

    private List<Egg> mEggList;
    private List<Pet> mPetList;

    public Player(String name, String password){
        mName = name;
        mPassword = password;
    }

    public String getName(){
        return mName;
    }

    public String getPassword(){
        return mPassword;
    }

    public List<Egg> getEggList(){
        return mEggList;
    }

    public void addEgg(Egg egg){
        mEggList.add(egg);
    }

    public List<Pet> getPetList(){
        return mPetList;
    }

    public void addPet(Pet pet){
        mPetList.add(pet);
    }

    public void setFileName(String fileName){ mFileName = fileName; }

    public String getFileName() { return mFileName; }

    public void setPrimary() { mPrimaryProfile = true; }

    public void removePrimary() { mPrimaryProfile = false; }

    public boolean isPrimary() { return mPrimaryProfile; }
}
