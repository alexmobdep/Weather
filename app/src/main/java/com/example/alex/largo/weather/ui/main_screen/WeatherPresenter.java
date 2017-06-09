package com.example.alex.largo.weather.ui.main_screen;
import android.location.Location;

public interface WeatherPresenter {
    void getWeatherInfo(Location location);
    void getWeatherInfo(String cityName);
    void onDestroy();
}
