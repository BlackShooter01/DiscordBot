package me.blackshooter01;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public final class StatsManager {
    public static void Przydziel(JSONObject statsObject, int var, String choice, long id) {
        long punkty = (long) statsObject.get("Punkty");
        if(Math.abs(var)<=punkty)
        {
            Logi("Gracz modyfikuje statystykę "+choice+" o wartość "+var,id);
            punkty -= Math.abs(var);
            long newValue = (long) statsObject.get(choice) + var;
            JSONParser jsonParser = new JSONParser();
            JSONObject obj = null;
            JSONObject oldStatsObject = (JSONObject) statsObject.clone();
            try(FileReader reader = new FileReader((config.anomalie+id+"/stats.json")))
            {
                obj = (JSONObject) jsonParser.parse(reader);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
            statsObject.replace("Punkty", punkty);
            statsObject.replace(choice, newValue);
            if(obj!=null)
            {
                String objString = obj.toString().replace(oldStatsObject.toString(), statsObject.toString());
                try (FileWriter writer = new FileWriter(config.anomalie+id+"/stats.json")) {
                    writer.write(objString);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    public static void Nadaj(JSONObject statsObject, int var, String choice, long id)
    {
        long newValue = (long) statsObject.get(choice) + var;
        JSONParser jsonParser = new JSONParser();
        JSONObject obj = null;
        JSONObject oldStatsObject = (JSONObject) statsObject.clone();
        try(FileReader reader = new FileReader((config.anomalie+id+"/stats.json")))
        {
            obj = (JSONObject) jsonParser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        statsObject.replace(choice, newValue);
        if(obj!=null)
        {
            String objString = obj.toString().replace(oldStatsObject.toString(), statsObject.toString());
            try (FileWriter writer = new FileWriter(config.anomalie+id+"/stats.json")) {
                writer.write(objString);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void Logi(String s, long id)
    {
        Timestamp czas = new Timestamp(System.currentTimeMillis());
        try(FileWriter writer = new FileWriter(config.anomalie+id+"/logs.txt",true))
        {
            writer.write(czas+":"+s+"\n");
        }
        catch (IOException e) { throw new RuntimeException(e);}
    }
}

