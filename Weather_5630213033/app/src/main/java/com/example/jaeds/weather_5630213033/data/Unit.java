package com.example.jaeds.weather_5630213033.data;

import org.json.JSONObject;

/**
 * Created by jaeds on 1/3/2016.
 */
public class Unit implements JSONPopulator {
    private String tempereture;
    @Override
    public void populate(JSONObject data) {
        tempereture = data.optString("tempereture");
    }

    public String getTempereture() {
        return tempereture;
    }
}
