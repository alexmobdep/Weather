package com.example.alex.largo.weather.ui.add_new_card;


import com.google.android.gms.maps.model.LatLng;

public interface OnDataPass {
    void passData(String cityName);
    void passData(LatLng latLng);
}
