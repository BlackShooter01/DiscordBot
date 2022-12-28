package me.blackshooter01;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.ArrayList;

public class ItemEmbbed {
    public static MessageEmbed WeaponEmbbed(Abnormality item)
    {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(item.name);
        switch (item.rank) {
            case "Daleth" -> eb.setColor(new Color(0x804000));
            case "Zayin" -> eb.setColor(new Color(0x00b000));
            case "Teth" -> eb.setColor(new Color(0x2020b0));
            case "Kaph" -> eb.setColor(new Color(0x00b0b0));
            case "He" -> eb.setColor(new Color(0xd0d000));
            case "Waw" -> eb.setColor(new Color(0x7000d0));
            case "Qoph" -> eb.setColor(new Color(0xe09000));
            case "Aleph" -> eb.setColor(new Color(0xbb0000));
            default -> eb.setColor(Color.BLACK);
        }
        eb.addField("Typ",item.type,true);
        eb.addField("Rodzaj",item.type2,true);
        eb.addField("Opis",item.description,false);
        eb.addField("Stopień",item.rank, false);
        eb.addField("Opis",item.description,false);
        eb.addField("Właściwości",item.attribute,false);
        eb.addField("Dodatkowe informacje",item.additional,false);
        return eb.build();
    }
    public static MessageEmbed ListaEGOEmbbed(ArrayList<Abnormality> lista)
    {
        EmbedBuilder eb = new EmbedBuilder();
        String inside = "";
        for (Abnormality item : lista) {
            inside=inside+item.name+"\n";
        }
        eb.addField("Lista EGO", inside ,false);
        return eb.build();
    }
}
