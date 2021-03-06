package com.example.eggrun.classes.pet;

import com.example.eggrun.classes.RunSession;

import java.util.List;

public class PetFactory {
    Pet pet = null;

    public Pet createPet(String petType, List<RunSession> runSessionList){
        if (petType == null){
            return null;
        }
        else if (petType.equalsIgnoreCase("COMMON")){
            pet = new CommonPet();
        }
        else if (petType.equalsIgnoreCase("UNCOMMON")){
            pet = new UncommonPet();
        }
        else if (petType.equalsIgnoreCase("RARE")){
            pet = new RarePet();
        }
        else if (petType.equalsIgnoreCase("LEGENDARY")){
            pet = new LegendaryPet();
        }
        else {
            return null;
        }

        pet.setRunSessionList(runSessionList);
        return pet;
    }
}
