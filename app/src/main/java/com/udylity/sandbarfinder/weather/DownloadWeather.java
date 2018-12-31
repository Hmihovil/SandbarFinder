package com.udylity.sandbarfinder.weather;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import com.udylity.sandbarfinder.WeatherActivity;
import com.udylity.sandbarfinder.WeatherFragment;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadWeather extends AsyncTask<String, Void, Bundle> {

    public static WeatherData weatherData = new WeatherData();

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?";
    private static final String QUERY_PARAM = "q";
    private static final String FORMAT_PARAM = "mode";
    private static final String UNITS_PARAM = "units";

    @Override
    protected Bundle doInBackground(String... city) {
        HttpURLConnection mHttpURLConnection = null;
        BufferedReader mBufferedReader = null;
        String weatherString = null;

        String format = "json";
        String units = "imperial";

        try {
            Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, city[0])
                    .appendQueryParameter(FORMAT_PARAM, format)
                    .appendQueryParameter(UNITS_PARAM, units)
                    .build();
            URL url = new URL(builtUri.toString());

            // Create the request to OpenWeatherMap, and open the connection
            mHttpURLConnection = (HttpURLConnection) url.openConnection();
            mHttpURLConnection.setRequestMethod("GET");
            mHttpURLConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = mHttpURLConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                weatherString = null;
            }
            mBufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = mBufferedReader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                weatherString = null;
            }
            weatherString = buffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
            weatherString = null;
        } finally {
            if (mHttpURLConnection != null) {
                mHttpURLConnection.disconnect();
            }
            if (mBufferedReader != null) {
                try {
                    mBufferedReader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            return new ParseJson().getWeatherFromJson(weatherString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bundle result) {
        super.onPostExecute(result);
        weatherData.setLocation(result.getString(ParseJson.OWM_NAME));
        weatherData.setSunrise(result.getString(ParseJson.OWM_SUNRISE));
        weatherData.setSunset(result.getString(ParseJson.OWM_SUNSET));
        weatherData.setWindDirection(result.getInt(ParseJson.OWM_WIND_DIRECTION));
        weatherData.setHumidity(result.getInt(ParseJson.OWM_HUMIDITY));
        weatherData.setWindSpeed(result.getInt(ParseJson.OWM_WIND_SPEED));

        WeatherFragment.mTextView.setText(weatherData.getData());
        WeatherActivity.mapFragment.setWindDirection();
    }
}