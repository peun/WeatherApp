package com.example.jaeds.weather_5630213033.data;

import org.json.JSONObject;

/**
 * Created by jaeds on 1/3/2016.
 */
public class Atmosphere implements JSONPopulator{
    private int humidity;

    public int getHumidity() {
        return humidity;
    }

    @Override
    public void populate(JSONObject data) {
        humidity = data.optInt("humidity");
    }
}
