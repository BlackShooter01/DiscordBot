package me.blackshooter01;

import org.json.simple.JSONObject;

public class Item extends Abnormality{
    public Item()
    {

    }
    public Item(JSONObject object)
    {
        this.setObject(object);
    }

}
