package me.blackshooter01;

import org.json.simple.JSONObject;

public class Stats extends Abnormality{
    public Stats(JSONObject o)
    {
        this.setObject(o);
    }
    public long getPoints(JSONObject o)
    {
        return (long) o.get("Points");
    }
    public JSONObject getFortitude()
    {
        return (JSONObject) this.object.get("Fortitude");
    }
    public long getStrength()
    {
        return (long) getFortitude().get("Strength");
    }
    public long getEndurance()
    {
        return (long) getFortitude().get("Endurance");
    }
    public long getReflex()
    {
        return (long) getFortitude().get("Reflex");
    }
    public JSONObject getPrudence()
    {
        return (JSONObject) this.object.get("Prudence");
    }
    public long getKnowledge()
    {
        return (long) getPrudence().get("Knowledge");
    }
    public long getAdaptation()
    {
        return (long) getPrudence().get("Adaptation");
    }
    public long getIntuition()
    {
        return (long) getPrudence().get("Intuition");
    }
    public JSONObject getTemperance()
    {
        return (JSONObject) this.object.get("Temperance");
    }
    public long getSpecial()
    {
        return (long) getTemperance().get("Special");
    }
    public String getSpecialName()
    {
        return (String) getTemperance().get("SpecialName");
    }
    public long getComposure()
    {
        return (long) getTemperance().get("Composure");
    }
    public long getIntegrity()
    {
        return (long) getTemperance().get("Integrity");
    }
    public JSONObject getJustice()
    {
        return (JSONObject) this.object.get("Justice");
    }
    public long getHeroism()
    {
        return (long) getJustice().get("Heroism");
    }
    public long getGenerosity()
    {
        return (long) getJustice().get("Generosity");
    }
    public long getKindness()
    {
        return (long) getJustice().get("Kindness");
    }
}
