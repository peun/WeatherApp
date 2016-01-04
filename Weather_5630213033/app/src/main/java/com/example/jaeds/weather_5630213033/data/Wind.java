package com.example.jaeds.weather_5630213033.data;

import org.json.JSONObject;

/**
 * Created by jaeds on 1/3/2016.
 */
public class Wind implements JSONPopulator {
    private int chill;
    private int desciption;
    private int speed;

    public int getChill() {
        return chill;
    }

    public int getDesciption() {
        return desciption;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void populate(JSONObject data) {
        chill = data.optInt("chill");
        desciption = data.optInt("direction");
        speed = data.optInt("speed");
    }
}
