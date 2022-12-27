package me.blackshooter01;

import java.awt.*;
import java.util.Random;

public abstract class Abnormality {
    public String name,type,type2,rank,description,attribute,additional;
    public Abnormality()
    {
        String[] list = {"Sword", "Axe", "Chainsword", "Bow", "Hat", "Dagger", "Scythe", "Ball", "Vortix"};
        String[] list2 = {"Daleth","Zayin","Teth","Kaph","He","Waw","Qoph","Aleph" };
        Random r1= new Random(),r2 = new Random();
        String choose = list[r1.nextInt(list.length)];
        String choose2=list2[r2.nextInt(list2.length)];
        this.name = "Cinder's "+choose;
        this.type = "Weapon EGO";
        this.type2 = choose;
        this.rank = choose2;
        this.description = "Jest to broń przypominająca "+choose+".";
        this.attribute = "- W stanie spoczynku broń posiada mnożniki: Cięte";
        this.additional = "Da się wyczuć od niego wewnętrzne, nostalgiczne ciepło.";
    }
}
