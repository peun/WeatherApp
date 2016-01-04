package com.example.jaeds.weather_5630213033;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jaeds.weather_5630213033.data.Atmosphere;
import com.example.jaeds.weather_5630213033.data.Channel;
import com.example.jaeds.weather_5630213033.data.Item;
import com.example.jaeds.weather_5630213033.data.Wind;
import com.example.jaeds.weather_5630213033.service.WeatherServiceCallback;
import com.example.jaeds.weather_5630213033.service.YahooWeatherService;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {
private ImageView weatherIcon;
    private TextView temperatureText;
    private TextView CoditText;
    private TextView locationText;
    private ProgressDialog dialog;
    private YahooWeatherService service;
    private TextView windd;
    private TextView humidity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherIcon = (ImageView)findViewById(R.id.weatherIconImageView);
        temperatureText=(TextView)findViewById(R.id.temperatureTextView);
        CoditText =(TextView)findViewById(R.id.conditionTextView);
        locationText = (TextView)findViewById(R.id.locationTextView);
        windd = (TextView)findViewById(R.id.windd);
        humidity = (TextView)findViewById(R.id.humidityy);
        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Load...");

        service.refresh("Phuket,Thailand");
    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        Item item = channel.getItem();
        Wind wind = channel.getWind();
        Atmosphere atmosphere = channel.getAtmosphere();
        int resource = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());

        @SuppressWarnings("deprecation")
        Drawable weatherIconDraw = getResources().getDrawable(resource);

        weatherIcon.setImageDrawable(weatherIconDraw);
        temperatureText.setText(item.getCondition().getTempereture() + "\u00B0 " + channel.getUnit().getTempereture());
        CoditText.setText(item.getCondition().getDescription());
        locationText.setText(service.getLocation());
        windd.setText("Wind "+String.valueOf(wind.getDesciption()));
        humidity.setText("Humidity "+String.valueOf(atmosphere.getHumidity()));


    }

    @Override
    public void serviceFail(Exception except) {
        dialog.hide();
        Toast.makeText(this,except.getMessage(),Toast.LENGTH_LONG).show();
    }
}
