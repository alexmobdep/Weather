package com.example.alex.largo.weather.model.open_weather;


import com.google.gson.annotations.SerializedName;

class Location {
    @SerializedName("lat")
    double lat;

    @SerializedName("lon")
    double lon;

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}
