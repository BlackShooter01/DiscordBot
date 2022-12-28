package me.blackshooter01;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.awt.*;
import java.util.ArrayList;

public class ItemEmbbed {
    public static MessageEmbed WeaponEmbbed(Abnormality item)
    {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(item.getName());
        switch (item.getRank()) {
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
        eb.addField("Typ",item.getType(),true);
        eb.addField("Rodzaj",item.getType2(),true);
        eb.addField("Stopień",item.getRank(), true);
        eb.addField("Opis",item.getDescription(),false);
        eb.addField("Właściwości",item.getAttribute(),false);
        eb.addField("Dodatkowe informacje",item.getAdditional(),false);
        if(item.getExtra()!=null){eb.addField("Dodatkowe",item.getExtra(),false);}
        return eb.build();
    }
    public static MessageEmbed ListaEGOEmbbed(ArrayList<Abnormality> lista)
    {
        EmbedBuilder eb = new EmbedBuilder();
        String inside = "";
        int indeks=1;
        for (Abnormality item : lista) {
            inside=inside+indeks+". "+item.getName()+"\n";
            indeks++;
        }
        eb.addField("Lista EGO", inside ,false);
        return eb.build();
    }
    public static MessageEmbed FailEGO()
    {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Nie odnaleziono podanego EGO!");
        return eb.build();
    }
}
