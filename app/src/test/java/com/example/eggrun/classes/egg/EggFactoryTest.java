package com.example.eggrun.classes.egg;

import org.junit.Test;

import static org.junit.Assert.*;

public class EggFactoryTest {
    EggFactory eggFactory = new EggFactory();
    @Test
    public void createEggCommon() {
        Egg egg = eggFactory.createEgg("COMMON");
        assertEquals(1.0,egg.DistanceToHatch(),0);
        assertNotNull(egg);
    }
    @Test
    public void createEggUncommon() {
        Egg egg = eggFactory.createEgg("UNCOMMON");
        assertEquals(3.0,egg.DistanceToHatch(),0);
        assertNotNull(egg);
    }
    @Test
    public void createEggRare() {
        Egg egg = eggFactory.createEgg("RARE");
        assertEquals(5.0,egg.DistanceToHatch(),0);
        assertNotNull(egg);
    }
    @Test
    public void createEggLegendary() {
        Egg egg = eggFactory.createEgg("Legendary");
        assertEquals(10.0,egg.DistanceToHatch(),0);
        assertNotNull(egg);
    }
}