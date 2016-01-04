package com.example.jaeds.weather_5630213033.data;

import org.json.JSONObject;

/**
 * Created by jaeds on 1/3/2016.
 */
public class Condition implements JSONPopulator {
    private int code;
    private int tempereture;
    private String description;

    public int getCode() {
        return code;
    }

    public int getTempereture() {
        return tempereture;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void populate(JSONObject data) {
        code = data.optInt("code");
        tempereture = data.optInt("temp");
        description = data.optString("text");
    }
}
