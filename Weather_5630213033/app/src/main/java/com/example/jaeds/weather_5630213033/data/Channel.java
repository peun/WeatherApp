package com.example.jaeds.weather_5630213033.data;

import org.json.JSONObject;

/**
 * Created by jaeds on 1/3/2016.
 */
public class Channel implements JSONPopulator
{
    private Unit unit;
    private Item item;
    private Atmosphere atmosphere;
    private Wind wind;

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public Wind getWind() {
        return wind;
    }

    public Unit getUnit() {
        return unit;
    }

    public Item getItem() {
        return item;
    }

    @Override
    public void populate(JSONObject data) {
        unit = new Unit();
        unit.populate(data.optJSONObject("units"));

        item = new Item();
        item.populate(data.optJSONObject("item"));

        wind = new Wind();
        wind.populate(data.optJSONObject("wind"));

        atmosphere = new Atmosphere();
       atmosphere.populate(data.optJSONObject("atmosphere"));
    }


}
