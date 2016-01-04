package com.example.jaeds.weather_5630213033.service;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.jaeds.weather_5630213033.data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by jaeds on 1/3/2016.
 */
public class YahooWeatherService {
    private WeatherServiceCallback callback;
    private String location;
    private Exception error;

    public YahooWeatherService(WeatherServiceCallback callback) {
        this.callback = callback;
    }

    public String getLocation() {
        return location;
    }

    public void refresh(final String l){
        this.location = l;
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {

              String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u ='c'",strings[0]);
               String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                try {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null){
                        result.append(line);
                    }

                    return result.toString();
                } catch (Exception e) {
                    error = e;

                }
                return null;
            }


            protected void onPostExecute(String s) {

                if ((s==null&& error != null)){
                    callback.serviceFail(error);
                    return;
                }
                try {
                    JSONObject data = new JSONObject(s);

                    JSONObject query = data.getJSONObject("query");

                    int count = query.optInt("count");

                    if(count==0){
                        callback.serviceFail(new LocationWeather("No weather"+location));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(query.optJSONObject("results").optJSONObject("channel"));
                    callback.serviceSuccess(channel);
                } catch (JSONException e) {
                   callback.serviceFail(e);
                }
            }
        }.execute(l);
    }

            public  class LocationWeather extends Exception
            {
                public LocationWeather(String detailMessage) {
                    super(detailMessage);
                }
            }
}
