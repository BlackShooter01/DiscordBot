package me.blackshooter01;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    public Abnormality Parser(long id, String name)
    {
        JSONParser jsonParser = new JSONParser();
        Abnormality abnormality = null;
        try (FileReader reader = new FileReader(config.anomalie+id+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray abnormalityList = (JSONArray) obj;

            //Iterate over item array
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
    public Abnormality Parser(long id, Integer indeks)
    {
        JSONParser jsonParser = new JSONParser();
        Abnormality abnormality = null;
        try (FileReader reader = new FileReader(config.anomalie+id+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray abnormalityList = (JSONArray) obj;
            if(abnormalityList.size()>indeks) { abnormality = parseAbnormalityObject((JSONObject) abnormalityList.get(indeks)); }
            //abnormalityList.forEach(emp -> parseAbnormalityObject((JSONObject) emp,id));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return abnormality;
    }
    public ArrayList<Abnormality> Parser(long id)
    {
        JSONParser jsonParser = new JSONParser();
        ArrayList<Abnormality> lista = new ArrayList<>();
        try (FileReader reader = new FileReader(config.anomalie+id+".json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray abnormalityList = (JSONArray) obj;

            //Iterate over item array
            for (Object o : abnormalityList) {
                Abnormality temp = parseAbnormalityObject((JSONObject) o);
                lista.add(temp);
            }
            //abnormalityList.forEach(emp -> parseAbnormalityObject((JSONObject) emp,id));

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
}
