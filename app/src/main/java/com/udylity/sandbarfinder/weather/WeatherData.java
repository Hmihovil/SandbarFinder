package com.udylity.sandbarfinder.weather;

import com.udylity.sandbarfinder.Direction;

public class WeatherData {
    private String location, windDirectionStr, sunrise, sunset;
    private int humidity, windSpeed;
    private static int windDirection = -1;

    public void setLocation(String loc) {
        this.location = loc;
    }

    public void setWindDirection(int windDirection) {
        if (windDirection >= 337.5 || windDirection < 22.5) {
            windDirectionStr = "North";
            this.windDirection = Direction.NORTH;
        } else if (windDirection >= 22.5 && windDirection < 67.5) {
            windDirectionStr = "Northeast";
            this.windDirection = Direction.NORTHEAST;
        } else if (windDirection >= 67.5 && windDirection < 112.5) {
            windDirectionStr = "East";
            this.windDirection = Direction.EAST;
        } else if (windDirection >= 112.5 && windDirection < 157.5) {
            windDirectionStr = "Southeast";
            this.windDirection = Direction.SOUTHEAST;
        } else if (windDirection >= 157.5 && windDirection < 202.5) {
            windDirectionStr = "South";
            this.windDirection = Direction.SOUTH;
        } else if (windDirection >= 202.5 && windDirection < 247.5) {
            windDirectionStr = "Southwest";
            this.windDirection = Direction.SOUTHWEST;
        } else if (windDirection >= 247.5 && windDirection < 292.5) {
            windDirectionStr = "West";
            this.windDirection = Direction.WEST;
        } else if (windDirection >= 292.5 || windDirection < 22.5) {
            windDirectionStr = "Northwest";
            this.windDirection = Direction.NORTHWEST;
        }else{
            windDirectionStr = "" + windDirection;
        }
    }

    public void setWindSpeed(int windSpeed){
        this.windSpeed = windSpeed;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public static int getWindDirection(){
        return windDirection;
    }

    public String getData(){
        String data = "Location: " + location + "\n" +
                      "Wind Direction: " + windDirectionStr + "\n" +
                      "Wind Speed: " + windSpeed + "mph\n" +
                      "Humidity: " + humidity + "%\n" +
                      "Sunrise: " + sunrise + "\n" +
                      "Sunset: " + sunset;
        return data;
    }

}
