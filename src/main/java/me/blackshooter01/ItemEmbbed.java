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
    public static MessageEmbed WelcomeChannelMessage()
    {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.WHITE);
        eb.addField("Witaj na swoim kanale technicznym!","",false);
        return eb.build();
    }
    public static MessageEmbed StatsEmbbed(Stats stats)
    {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.lightGray);
        eb.setTitle("Statystyki");
        String dodatek = "";
        long punkty = stats.getPoints(stats.getFortitude());
        if(punkty!=0) { dodatek=" ("+punkty+")";}
        eb.addField("Męstwo"+dodatek,"Krzepa: "+stats.getStrength()+"\nOdporność: "+stats.getEndurance()+"\nRefleks: "+stats.getReflex(),false);
        punkty = stats.getPoints(stats.getPrudence());
        dodatek ="";
        if(punkty!=0) { dodatek=" ("+punkty+")";}
        eb.addField("Mądrość"+dodatek,"Wiedza: "+stats.getKnowledge()+"\nPrzystosowanie: "+stats.getAdaptation()+"\nIntuicja: "+stats.getIntuition(),false);
        punkty = stats.getPoints(stats.getTemperance());
        dodatek ="";
        if(punkty!=0) { dodatek=" ("+punkty+")";}
        eb.addField("Roztropność"+dodatek,stats.getSpecialName()+": "+stats.getSpecial()+"\nOpanowanie: "+stats.getComposure()+"\nIntegralność duszy: "+stats.getIntegrity(),false);
        punkty = stats.getPoints(stats.getJustice());
        dodatek ="";
        if(punkty!=0) { dodatek=" ("+punkty+")";}
        eb.addField("Sprawiedliwość"+dodatek,"Bohaterstwo: "+stats.getHeroism()+"\nHojność: "+stats.getGenerosity()+"\nSerdeczność: "+stats.getKindness(),false);
        return eb.build();
    }
}
