package com.example.alex.largo.weather.model.open_weather;

import com.google.gson.annotations.SerializedName;

public class Main {
    @SerializedName("temp")
    double temp;

    @SerializedName("humidity")
    int humidity;

    @SerializedName("pressure")
    double pressure;

    @SerializedName("temp_min")
    String tempMin;

    @SerializedName("temp_max")
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
