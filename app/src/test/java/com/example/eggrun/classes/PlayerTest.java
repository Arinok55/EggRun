package com.example.eggrun.classes;

import com.example.eggrun.classes.egg.UncommonEgg;
import com.example.eggrun.classes.pet.CommonPet;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {


    @Test
    public void getName() {
        Player player = new Player("Name", "File");
        assertEquals("Name", player.getName());
    }

    @Test
    public void getEggList() {
        Player player = new Player("Name", "File");
        assertEquals(0,player.getEggList().size());
    }

    @Test
    public void addEgg() {
        Player player = new Player("Name", "File");
        player.addEgg(new UncommonEgg());
        assertEquals(1,player.getEggList().size());
    }

    @Test
    public void removeEgg() {
        Player player = new Player("Name", "File");
        UncommonEgg egg = new UncommonEgg();
        player.addEgg(egg);
        player.removeEgg(egg);
        assertEquals(0,player.getEggList().size());
    }

    @Test
    public void getPetList() {
        Player player = new Player("Name", "File");
        player.addEgg(new UncommonEgg());
        assertEquals(1,player.getEggList().size());
    }

    @Test
    public void addPet() {
        Player player = new Player("Name", "File");
        player.addPet(new CommonPet(new UncommonEgg()));
        assertEquals(1,player.getPetList().size());
    }
}