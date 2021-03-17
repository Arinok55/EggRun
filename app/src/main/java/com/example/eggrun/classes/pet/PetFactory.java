package com.example.eggrun.classes.pet;

import com.example.eggrun.classes.egg.Egg;

public class PetFactory {
    Pet pet = null;

    public Pet createPet(Egg egg){
        String petType = egg.toString();
        if (petType == null){
            return null;
        }
        else if (petType.equalsIgnoreCase("COMMON")){
            pet = new CommonPet(egg);
        }
        else if (petType.equalsIgnoreCase("UNCOMMON")){
            pet = new UncommonPet(egg);
        }
        else if (petType.equalsIgnoreCase("RARE")){
            pet = new RarePet(egg);
        }
        else if (petType.equalsIgnoreCase("LEGENDARY")){
            pet = new LegendaryPet(egg);
        }
        return null;
    }
}
