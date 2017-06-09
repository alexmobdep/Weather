package com.example.alex.largo.weather.model.open_weather;


import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("speed")
    double speed;

    @SerializedName("deg")
    double deg;

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }
}
