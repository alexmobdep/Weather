package com.example.alex.largo.weather.model.open_weather;


import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("country")
    private
    String country;

    @SerializedName("sunrise")
    private
    int sunrise;

    @SerializedName("sunset")
    private
    int sunset;

    public String getCountry() {
        return country;
    }

    public int getSunrise() {
        return sunrise;
    }

    public int getSunset() {
        return sunset;
    }
}
