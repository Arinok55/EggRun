package com.example.eggrun.classes;

import com.example.eggrun.classes.pet.CommonPet;
import com.example.eggrun.classes.egg.Egg;

import java.util.List;

public class Player {
    private static Player player = null;
    private String name = null;
    private List<Egg> eggList = null;
    private List<CommonPet> petList = null;

    private Player(){/* Singleton design */}

    public static Player createPlayer(String name){
        if (player == null){
            player = new Player();
            player.name = name;
        }
        return player;
    }

    public boolean addEgg(Egg egg){
        return eggList.add(egg);
    }

    public boolean removeEgg(Egg egg){
        if (eggList.contains(egg)){
            return eggList.remove(egg);
        }
        return false;
    }

    public boolean addPet(CommonPet pet){
        return petList.add((pet));
    }

    public boolean removeEgg(CommonPet pet){
        if (petList.contains(pet)){
            return petList.remove(pet);
        }
        return false;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        player.name = name;
    }
}
