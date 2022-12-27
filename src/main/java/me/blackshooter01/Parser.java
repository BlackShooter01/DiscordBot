package me.blackshooter01;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.ArrayList;

public class Parser {
    public void Parser()
    {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(config.anomalie))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray abnormalityList = (JSONArray) obj;

            //Iterate over employee array
            abnormalityList.forEach(emp -> parseAbnormalityObject((JSONObject) emp) );

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }
    private static void parseAbnormalityObject(JSONObject abnormality)
    {
        //Get employee object within list
        JSONObject abnormalityObject = (JSONObject) abnormality.get("item");

        //Get employee first name
        String firstName = (String) abnormalityObject.get("Właściciel");

        //Get employee last name
        String lastName = (String) abnormalityObject.get("Nazwa");

        //Get employee website name
        String website = (String) abnormalityObject.get("Opis");
    }
    public ArrayList<Abnormality> Parser(long id)
    {
        JSONParser jsonParser = new JSONParser();
        Abnormality item=null;
        ArrayList<Abnormality> lista = new ArrayList<Abnormality>();
        try (FileReader reader = new FileReader(config.anomalie))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray abnormalityList = (JSONArray) obj;

            //Iterate over item array
            for (Object o : abnormalityList) {
                Abnormality temp = (Abnormality) parseAbnormalityObject((JSONObject) o, id);
                if (temp != null) {
                    lista.add(temp);
                }
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
    private Abnormality parseAbnormalityObject(JSONObject abnormality, long id)
    {
        //Get employee object within list
        JSONObject abnormalityObject = (JSONObject) abnormality.get("item");

        //Get employee first name
        String firstName = (String) abnormalityObject.get("Właściciel");
        if(firstName.equals(Long.toString(id)))
        {
            System.out.println("Zgadza się!");
            String nazwa = (String) abnormalityObject.get("Nazwa");
            String typ = (String) abnormalityObject.get("Typ");
            String rodzaj = (String) abnormalityObject.get("Rodzaj");
            String rank = (String) abnormalityObject.get("Stopień");
            String opis = (String) abnormalityObject.get("Opis");
            String atrybut = (String) abnormalityObject.get("Właściwości");
            String dodatkowe = (String) abnormalityObject.get("Inne informacje");
            Item item = new Item();
            item.Abnormality(nazwa,typ,rodzaj,rank,opis,atrybut,dodatkowe);
            return item;
        }
        return null;
        //Get employee last name
        //String lastName = (String) abnormalityObject.get("Nazwa");

        //Get employee website name
        //String website = (String) abnormalityObject.get("Opis");
    }
}
