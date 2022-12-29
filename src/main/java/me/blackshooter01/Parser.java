package me.blackshooter01;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public final class Parser {
    public static Abnormality ItemParser(long id, String name)
    {
        JSONParser jsonParser = new JSONParser();
        Abnormality abnormality = null;
        try (FileReader reader = new FileReader(config.anomalie+id+"/items.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray abnormalityList = (JSONArray) obj;

            for (Object o : abnormalityList) {
                Abnormality temp = parseObject((JSONObject) o);
                if (temp!=null && temp.getName().equals(name)){
                    abnormality = temp;
                    break;
                }
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return abnormality;
    }
    public static Abnormality ItemParser(long id, Integer indeks)
    {
        JSONParser jsonParser = new JSONParser();
        Abnormality abnormality = null;
        try (FileReader reader = new FileReader(config.anomalie+id+"/items.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray abnormalityList = (JSONArray) obj;

            if(abnormalityList.size()>indeks) { abnormality = parseObject((JSONObject) abnormalityList.get(indeks)); }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return abnormality;
    }
    public static ArrayList<Abnormality> ItemParser(long id)
    {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Abnormality> lista = new ArrayList<>();
        try (FileReader reader = new FileReader(config.anomalie+id+"/items.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray abnormalityList = (JSONArray) obj;

            for (Object o : abnormalityList) {
                JSONObject object = (JSONObject) o;
                Abnormality temp = parseObject(object);
                if(temp!=null && temp.getClass().equals(Item.class)) { lista.add(temp); }
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
    public static Abnormality AbilityParser(long id, String name)
    {
        JSONParser jsonParser = new JSONParser();
        Abnormality abnormality = null;
        try (FileReader reader = new FileReader(config.anomalie+id+"/abilities.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray abnormalityList = (JSONArray) obj;

            for (Object o : abnormalityList) {
                Abnormality temp = parseObject((JSONObject) o);
                if (temp!=null && temp.getName().equals(name)) {
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
    public static Abnormality AbilityParser(long id, int indeks)
    {
        JSONParser jsonParser = new JSONParser();
        Abnormality abnormality = null;
        try (FileReader reader = new FileReader(config.anomalie+id+"/abilities.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray abnormalityList = (JSONArray) obj;

            if(abnormalityList.size()>indeks) { abnormality = parseObject((JSONObject) abnormalityList.get(indeks)); }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return abnormality;
    }
    public static ArrayList<Abnormality> AbilityParser(long id)
    {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Abnormality> lista = new ArrayList<>();
        try (FileReader reader = new FileReader(config.anomalie+id+"/abilities.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray abnormalityList = (JSONArray) obj;

            for (Object o : abnormalityList) {
                JSONObject object = (JSONObject) o;
                if(object.containsKey("ability")) {lista.add(parseObject(object)); }
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
    private static Abnormality parseObject(JSONObject o)
    {
        JSONObject object;
        object = (JSONObject) o.get("item");
        if(object!=null) { return new Item(object); }
        object = (JSONObject) o.get("ability");
        if(object!=null) { return new Ability(object); }
        return null;
    }
}
