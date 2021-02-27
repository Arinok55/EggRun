package com.example.eggrun;

import androidx.annotation.NonNull;

import java.util.List;

public class Egg {
    private enum RARITY {common, uncommon, rare, legendary}

    private RARITY value = null;
    private static Egg common = null;
    private static Egg uncommon = null;
    private static Egg rare = null;
    private static Egg legendary = null;

    private List<RunSession> RunSessionList = null;

    private Egg() {/* Singleton constructor */}

    public static Egg CommonEggCreate(){
        if (common == null){
            common = new Egg();
            common.value = RARITY.common;
        }
        return common;
    }

    public static Egg UncommonEggCreate(){
        if (uncommon == null){
            uncommon = new Egg();
            uncommon.value = RARITY.uncommon;
        }
        return uncommon;
    }

    public static Egg RareEggCreate(){
        if (rare == null){
            rare = new Egg();
            rare.value = RARITY.rare;
        }
        return rare;
    }

    public static Egg LegendaryEggCreate(){
        if (legendary == null){
            legendary = new Egg();
            legendary.value = RARITY.legendary;
        }
        return legendary;
    }

    public void addRunSession(RunSession runSession){
        RunSessionList.add(runSession);
    }

    public List<RunSession> getRunSessionList(){
        return RunSessionList;
    }

    @NonNull
    public String toString() {
        if (value == RARITY.common){
            return "common";
        }
        else if (value == RARITY.uncommon) {
            return "uncommon";
        }
        else if (value == RARITY.rare) {
            return "rare";
        }
        else if (value == RARITY.legendary) {
            return "legendary";
        }
        return "";
    }
}

