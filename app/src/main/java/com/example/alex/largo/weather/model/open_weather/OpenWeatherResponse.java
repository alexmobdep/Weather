package com.example.alex.largo.weather.model.open_weather;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class OpenWeatherResponse {

    @SerializedName("coord")
    private
    Location location;

    @SerializedName("sys")
    private
    Sys sys;

    @SerializedName("weather")
    private
    List<Weather> weatherList;

    @SerializedName("main")
    private
    Main main;

    @SerializedName("wind")
    private
    Wind wind;

    @SerializedName("rain")
    private
    Map<String, Integer> rain;

    @SerializedName("clouds")
    private
    Map<String, Integer> clouds;

    @SerializedName("dt")
    private
    int dt;

    @SerializedName("id")
    private
    int id;

    @SerializedName("name")
    private
    String name;

    @SerializedName("cod")
    private
    int cod;

    public Location getLocation() {
        return location;
    }

    public Sys getSys() {
        return sys;
    }

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public Main getMain() {
        return main;
    }

    public Wind getWind() {
        return wind;
    }

    public Map<String, Integer> getRain() {
        return rain;
    }

    public Map<String, Integer> getClouds() {
        return clouds;
    }

    public int getDt() {
        return dt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCod() {
        return cod;
    }
}
