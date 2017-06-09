package com.example.alex.largo.weather.model.open_weather;


import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("country")
    String country;

    @SerializedName("sunrise")
    int sunrise;

    @SerializedName("sunset")
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
