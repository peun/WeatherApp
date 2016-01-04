package com.example.jaeds.weather_5630213033.service;

import com.example.jaeds.weather_5630213033.data.Channel;

/**
 * Created by jaeds on 1/3/2016.
 */
public interface WeatherServiceCallback {
    void serviceSuccess (Channel channel);
    void serviceFail(Exception except);
}
