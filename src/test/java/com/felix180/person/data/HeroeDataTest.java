package com.felix180.person.data;

import com.felix180.person.entity.Heroe;

import java.util.ArrayList;
import java.util.List;

public class HeroeDataTest {

    public static final String SUPER_MAN = "SuperMan";
    public static final long ID_1 = 1L;
    public static final long ID_2 = 2L;
    public static final String SPIDERMAN = "Spiderman";

    public static List<Heroe> getHeroeList(){
        List<Heroe> heroeList = new ArrayList<>();
        heroeList.add(getHeroeSuperMan());
        heroeList.add(getHeroeSpiderman());
        return heroeList;
    };


    public static  Heroe getHeroeSuperMan(){
        Heroe heroe = new Heroe(ID_1, SUPER_MAN);
        return heroe;
    }

    public static  Heroe getHeroeSpiderman(){
        Heroe heroe = new Heroe(ID_2, SPIDERMAN);
        return heroe;
    }
}
