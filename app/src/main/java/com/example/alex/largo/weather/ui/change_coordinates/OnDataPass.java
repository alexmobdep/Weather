package com.example.alex.largo.weather.ui.change_coordinates;


import com.google.android.gms.maps.model.LatLng;

public interface OnDataPass {
    void passData(String cityName);
    void passData(LatLng latLng);
}
