package com.example.eggrun;

import androidx.annotation.NonNull;

public class Egg {
    private enum RARETY {common, uncommon, rare, legendary}

    private RARETY value = null;
    private static Egg common = null;
    private static Egg uncommon = null;
    private static Egg rare = null;
    private static Egg legendary = null;

    private Egg() {/* Singleton constructor */}

    public static Egg CommonEggCreate(){
        if (common == null){
            common = new Egg();
            common.value = RARETY.common;
        }
        return common;
    }

    public static Egg UnCommonEggCreate(){
        if (uncommon == null){
            uncommon = new Egg();
            uncommon.value = RARETY.uncommon;
        }
        return uncommon;
    }

    public static Egg RareEggCreate(){
        if (rare == null){
            rare = new Egg();
            rare.value = RARETY.rare;
        }
        return rare;
    }

    public static Egg LegendaryEggCreate(){
        if (legendary == null){
            legendary = new Egg();
            legendary.value = RARETY.legendary;
        }
        return legendary;
    }

    @NonNull
    public String toString() {
        if (value == RARETY.common){
            return "common";
        }
        else if (value == RARETY.uncommon) {
            return "uncommon";
        }
        else if (value == RARETY.rare) {
            return "rare";
        }
        else if (value == RARETY.legendary) {
            return "legendary";
        }
        return "";
    }
}

