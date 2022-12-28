package me.blackshooter01;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    public Abnormality ItemParser(long id, String name)
    {
        JSONParser jsonParser = new JSONParser();
        Abnormality abnormality = null;
        try (FileReader reader = new FileReader(config.anomalie+id+"_items.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray abnormalityList = (JSONArray) obj;

            for (Object o : abnormalityList) {
                Abnormality temp = parseAbnormalityObject((JSONObject) o);
                if (temp.name.equals(name)) {
                    abnormality = temp;
                    break;
                }
            }
            //abnormalityList.forEach(emp -> parseAbnormalityObject((JSONObject) emp,id));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return abnormality;
    }
    public Abnormality ItemParser(long id, Integer indeks)
    {
        JSONParser jsonParser = new JSONParser();
        Abnormality abnormality = null;
        try (FileReader reader = new FileReader(config.anomalie+id+"_items.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray abnormalityList = (JSONArray) obj;

            if(abnormalityList.size()>indeks) { abnormality = parseAbnormalityObject((JSONObject) abnormalityList.get(indeks)); }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return abnormality;
    }
    public ArrayList<Abnormality> ItemParser(long id)
    {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Abnormality> lista = new ArrayList<>();
        try (FileReader reader = new FileReader(config.anomalie+id+"_items.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray abnormalityList = (JSONArray) obj;

            for (Object o : abnormalityList) {
                JSONObject object = (JSONObject) o;
                if(object.containsKey("item")) {lista.add(parseAbnormalityObject(object)); }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        if (lista.isEmpty())
        {
            return null;
        }
        else {
            return lista;
        }
    }
    private Abnormality parseAbnormalityObject(JSONObject abnormality)
    {
        //Get employee object within list
        JSONObject abnormalityObject = (JSONObject) abnormality.get("item");
        if(abnormalityObject==null) {return null;}

        String nazwa = (String) abnormalityObject.get("Nazwa");
        String typ = (String) abnormalityObject.get("Typ");
        String rodzaj = (String) abnormalityObject.get("Rodzaj");
        String rank = (String) abnormalityObject.get("Stopień");
        String opis = (String) abnormalityObject.get("Opis");
        String atrybut = (String) abnormalityObject.get("Właściwości");
        String dodatkowe = (String) abnormalityObject.get("Inne informacje");
        String extra = (String) abnormalityObject.get("Dodatkowe");
        Abnormality item = new Item();
        item.Abnormality(nazwa,typ,rodzaj,rank,opis,atrybut,dodatkowe,extra);
        return item;
    }

    public Abnormality AbilityParser(long id, String name)
    {
        JSONParser jsonParser = new JSONParser();
        Abnormality abnormality = null;
        try (FileReader reader = new FileReader(config.anomalie+id+"_abilities.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray abnormalityList = (JSONArray) obj;

            for (Object o : abnormalityList) {
                Abnormality temp = parseAbilityObject((JSONObject) o);
                if (temp.name.equals(name)) {
                    abnormality = temp;
                    break;
                }
            }
            //abnormalityList.forEach(emp -> parseAbnormalityObject((JSONObject) emp,id));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return abnormality;
    }
    public Abnormality AbilityParser(long id, Integer indeks)
    {
        JSONParser jsonParser = new JSONParser();
        Abnormality abnormality = null;
        try (FileReader reader = new FileReader(config.anomalie+id+"_abilities.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray abnormalityList = (JSONArray) obj;

            if(abnormalityList.size()>indeks) { abnormality = parseAbilityObject((JSONObject) abnormalityList.get(indeks)); }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return abnormality;
    }
    public ArrayList<Abnormality> AbilityParser(long id)
    {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Abnormality> lista = new ArrayList<>();
        try (FileReader reader = new FileReader(config.anomalie+id+"_abilities.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray abnormalityList = (JSONArray) obj;

            for (Object o : abnormalityList) {
                JSONObject object = (JSONObject) o;
                if(object.containsKey("ability")) {lista.add(parseAbilityObject(object)); }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        if (lista.isEmpty())
        {
            return null;
        }
        else {
            return lista;
        }
    }
    private Abnormality parseAbilityObject(JSONObject abnormality)
    {
        JSONObject abilityObject = (JSONObject) abnormality.get("ability");
        if(abilityObject==null) {return null;}

        String nazwa = (String) abilityObject.get("Nazwa");
        String typ = (String) abilityObject.get("Typ");
        String rodzaj = (String) abilityObject.get("Rodzaj");
        String rank = (String) abilityObject.get("Stopień");
        String opis = (String) abilityObject.get("Opis");
        String atrybut = (String) abilityObject.get("Właściwości");
        String dodatkowe = (String) abilityObject.get("Inne informacje");
        String extra = (String) abilityObject.get("Dodatkowe");
        Abnormality ability = new Ability();
        ability.Abnormality(nazwa,typ,rodzaj,rank,opis,atrybut,dodatkowe,extra);
        return ability;
    }
}
