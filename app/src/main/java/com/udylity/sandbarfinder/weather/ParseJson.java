package com.udylity.sandbarfinder.weather;

import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ParseJson {


    private static final String OWM_WEATHER = "weather";
    private static final String OWM_SYSTEM = "sys";
    public static final String OWM_SUNRISE = "sunrise";
    public static final String OWM_SUNSET = "sunset";
    public static final String OWM_TEMPERATURE = "temp";
    public static final String OWM_NAME = "name";
    private static final String OWM_MAIN = "main";
    public static final String OWM_HUMIDITY = "humidity";
    private static final String OWM_WIND = "wind";
    public static final String OWM_WIND_SPEED = "speed";
    public static final String OWM_WIND_DIRECTION = "deg";

    private String name, sunrise, sunset;
    private int windDirection, windSpeed, temperature, humidity;

    public Bundle getWeatherFromJson(String weatherString) throws JSONException {

        JSONObject mJsonObject = new JSONObject(weatherString);
        name = mJsonObject.getString(OWM_NAME);

        JSONObject windJsonObject = mJsonObject.getJSONObject(OWM_WIND);
        windSpeed = (int) Math.round(windJsonObject.getDouble(OWM_WIND_SPEED));
        windDirection = windJsonObject.getInt(OWM_WIND_DIRECTION);

        JSONObject mainJsonObject = mJsonObject.getJSONObject(OWM_MAIN);
        temperature = (int) Math.round(mainJsonObject.getDouble(OWM_TEMPERATURE));
        humidity = mainJsonObject.getInt(OWM_HUMIDITY);

        JSONObject sysJsonObject = mJsonObject.getJSONObject(OWM_SYSTEM);
        sunrise = Utility.getReadableDateString(sysJsonObject.getLong(OWM_SUNRISE));
        sunset = Utility.getReadableDateString(sysJsonObject.getLong(OWM_SUNSET));

        JSONObject weatherObject =  mJsonObject.getJSONArray(OWM_WEATHER).getJSONObject(0);

        Bundle mBundle = new Bundle();
        mBundle.putString(OWM_NAME, name);
        mBundle.putString(OWM_SUNRISE, sunrise);
        mBundle.putString(OWM_SUNSET, sunset);
        mBundle.putInt(OWM_WIND_DIRECTION, windDirection);
        mBundle.putInt(OWM_WIND_SPEED, windSpeed);
        mBundle.putInt(OWM_TEMPERATURE, temperature);
        mBundle.putInt(OWM_HUMIDITY, humidity);

        return mBundle;
    }

}
