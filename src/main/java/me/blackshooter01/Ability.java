package me.blackshooter01;

import org.json.simple.JSONObject;

public class Ability extends Abnormality{
    public Ability()
    {
        super();
    }
    public Ability(JSONObject object)
    {
        this.setObject(object);
    }
}
