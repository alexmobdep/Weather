package com.example.alex.largo.weather.model.open_weather;

import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    private
    double temp;

    @SerializedName("humidity")
    private
    int humidity;

    @SerializedName("pressure")
    private
    double pressure;

    @SerializedName("temp_min")
    private
    String tempMin;

    @SerializedName("temp_max")
    private
    String tempMax;

    public double getTemp() {
        return temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public String getTempMin() {
        return tempMin;
    }

    public String getTempMax() {
        return tempMax;
    }
}
