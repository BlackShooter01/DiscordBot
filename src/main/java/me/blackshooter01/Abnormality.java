package me.blackshooter01;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Random;

public abstract class Abnormality {
    public String name,type,type2,rank,description,attribute,additional,extra;
    protected JSONObject object;
    public Abnormality()
    {
        String[] list = {"Sword", "Axe", "Chainsword", "Bow", "Hat", "Dagger", "Scythe", "Ball", "Vortix"};
        String[] list2 = {"Daleth","Zayin","Teth","Kaph","He","Waw","Qoph","Aleph"};
        String[] list3 = {"Cinder's", "Dark's", "God's" ,"Someone's", "Dreamer's", "Weaver's", "Brutal", "Beta's", "Alpha's", "Prototype ", "Alpha", "Beta", "Omega", "Broken", "Authority's"};
        String[] list4 = {"ognia", "zimna", "światła", "wiatru", "ciemności", "ziemi", "porządku", "chaosu"};
        Random random= new Random();
        String dodatek="";
        if(random.nextInt(100)<25) {
            dodatek="Dodatkowo otrzymuje mnożnik "+String.format("%.2f",random.nextDouble(0,4))+" od "+list4[random.nextInt(list4.length)]+".";
        }
        String choose = list[random.nextInt(list.length)];
        String choose2=list2[random.nextInt(list2.length)];
        String stringToParse = "{\"Nazwa\":\""+list3[random.nextInt(list3.length)]+" "+choose+"\","
                                +"\"Typ\":\"Weapon EGO\","
                                +"\"Rodzaj\":\""+choose+"\","
                                +"\"Stopień\":\""+choose2+"\","
                                +"\"Opis\":\""+"Jest to broń przypominająca "+choose.toLowerCase()+".\","
                                +"\"Właściwości\":\"- Broń otrzymuje mnożniki fizyczne: Cięte - "+String.format("%.2f",random.nextDouble(0,2))+"x, Obuchowe - "+String.format("%.2f",random.nextDouble(0,2))+"x Przebijające - "+String.format("%.2f",random.nextDouble(0,2))+"x."+dodatek+"\","
                                +"\"Inne informacje\":\"Chaotycznie buzuje w dotyku.\"}";
        JSONParser parser = new JSONParser();
        try {
            this.object=(JSONObject) parser.parse(stringToParse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void setObject(JSONObject object)
    {
        this.object=object;
    }
    public String getName()
    {
        return (String) this.object.get("Nazwa");
    }
    public String getType()
    {
        return (String) this.object.get("Typ");
    }
    public String getType2()
    {
        return (String) this.object.get("Rodzaj");
    }
    public String getRank()
    {
        return (String) this.object.get("Stopień");
    }
    public String getDescription()
    {
        return (String) this.object.get("Opis");
    }
    public String getAttribute()
    {
        return (String) this.object.get("Właściwości");
    }
    public String getAdditional()
    {
        return (String) this.object.get("Inne informacje");
    }
    public String getExtra()
    {
        return (String) this.object.get("Dodatkowe");
    }
}
