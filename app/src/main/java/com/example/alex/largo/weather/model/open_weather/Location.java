package com.example.alex.largo.weather.model.open_weather;


import com.google.gson.annotations.SerializedName;

class Location {
    @SerializedName("lat")
    private
    double lat;

    @SerializedName("lon")
    private
    double lon;

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
