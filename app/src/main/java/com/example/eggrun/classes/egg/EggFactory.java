package com.example.eggrun.classes.egg;

public class EggFactory {

    public Egg createEgg(String eggType){
        if (eggType == null){
            return null;
        }
        else if (eggType.equalsIgnoreCase("COMMON")){
            return new CommonEgg();
        }
        else if (eggType.equalsIgnoreCase("UNCOMMON")){
            return new UncommonEgg();
        }
        else if (eggType.equalsIgnoreCase("RARE")){
            return new RareEgg();
        }
        else if (eggType.equalsIgnoreCase("LEGENDARY")){
            return new LegendaryEgg();
        }

        return null;
    }
}
