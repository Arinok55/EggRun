package com.example.eggrun;

import java.util.List;

public class Player {
    private static Player player = null;
    private String name = null;
    private List<Egg> eggList = null;
    private List<Pet> petList = null;

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

    public boolean addPet(Pet pet){
        return petList.add((pet));
    }

    public boolean removeEgg(Pet pet){
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
