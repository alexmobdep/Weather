package com.example.alex.largo.weather.ui.main_screen;

import com.example.alex.largo.weather.model.open_weather.OpenWeatherResponse;

public interface WeatherView {
    void showErrorDialog();
    void hideProgress();
    void inflateData(OpenWeatherResponse response);
}
