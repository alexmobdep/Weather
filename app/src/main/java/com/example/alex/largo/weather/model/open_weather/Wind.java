package com.example.alex.largo.weather.model.open_weather;


import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("speed")
    private
    double speed;

    @SerializedName("deg")
    private
    double deg;

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }
}
