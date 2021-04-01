package com.example.eggrun.classes.pet;

import com.example.eggrun.classes.egg.Egg;

public class PetFactory {

    public Pet createPet(Egg egg){
        String petType = egg.toString();
        if (petType.equalsIgnoreCase("COMMON")){
            return new CommonPet(egg);
        }
        else if (petType.equalsIgnoreCase("UNCOMMON")){
            return new UncommonPet(egg);
        }
        else if (petType.equalsIgnoreCase("RARE")){
            return new RarePet(egg);
        }
        else if (petType.equalsIgnoreCase("LEGENDARY")){
            return new LegendaryPet(egg);
        }
        return null;
    }
}
