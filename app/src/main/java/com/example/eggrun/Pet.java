package com.example.eggrun;

import android.media.Image;

import java.util.List;

public class Pet {
    private static Pet pet = null;
    private static Image image = null;
    private List<RunSession> RunSessionList = null;

    private Pet(){/*Singleton design*/}

    //private Image petImage(String rarity){ Add pet image }

    public static Pet createCommonPet(List<RunSession> list){
        if (pet == null){
            pet = new Pet();
            pet.RunSessionList = list;
            //pet.image = petImage("common");
        }
        return pet;
    }

    public static Pet createUncommonPet(List<RunSession> list){
        if (pet == null){
            pet = new Pet();
            pet.RunSessionList = list;
            //pet.image = petImage("uncommon");
        }
        return pet;
    }

    public static Pet createRarePet(List<RunSession> list){
        if (pet == null){
            pet = new Pet();
            pet.RunSessionList = list;
            //pet.image = petImage("rare");
        }
        return pet;
    }

    public static Pet createLegendaryPet(List<RunSession> list){
        if (pet == null){
            pet = new Pet();
            pet.RunSessionList = list;
            //pet.image = petImage("legendary");
        }
        return pet;
    }

    public List<RunSession> getRunSessionList(){
        return RunSessionList;
    }

    public Image getImage(){
        return image;
    }
}
