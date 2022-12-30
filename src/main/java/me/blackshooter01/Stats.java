package me.blackshooter01;

import org.json.simple.JSONObject;

public class Stats extends Abnormality{
    public Stats(JSONObject o)
    {
        this.setObject(o);
    }
    public long getPoints(JSONObject o)
    {
        return (long) o.get("Punkty");
    }
    public JSONObject getFortitude()
    {
        return (JSONObject) this.object.get("Fortitude");
    }
    public long getStrength()
    {
        return (long) getFortitude().get("Krzepa");
    }
    public long getEndurance()
    {
        return (long) getFortitude().get("Odporność");
    }
    public long getReflex()
    {
        return (long) getFortitude().get("Refleks");
    }
    public JSONObject getPrudence()
    {
        return (JSONObject) this.object.get("Prudence");
    }
    public long getKnowledge()
    {
        return (long) getPrudence().get("Wiedza");
    }
    public long getAdaptation()
    {
        return (long) getPrudence().get("Przystosowanie");
    }
    public long getIntuition()
    {
        return (long) getPrudence().get("Intuicja");
    }
    public JSONObject getTemperance()
    {
        return (JSONObject) this.object.get("Temperance");
    }
    public long getSpecial()
    {
        return (long) getTemperance().get("Unikalna");
    }
    public String getSpecialName()
    {
        return (String) getTemperance().get("UnikalnaNazwa");
    }
    public long getComposure()
    {
        return (long) getTemperance().get("Opanowanie");
    }
    public long getIntegrity()
    {
        return (long) getTemperance().get("Integralność duszy");
    }
    public JSONObject getJustice()
    {
        return (JSONObject) this.object.get("Justice");
    }
    public long getHeroism()
    {
        return (long) getJustice().get("Bohaterstwo");
    }
    public long getGenerosity()
    {
        return (long) getJustice().get("Hojność");
    }
    public long getKindness()
    {
        return (long) getJustice().get("Serdeczność");
    }
}
