package com.example.alex.largo.weather.utils;


import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class LocationUtils {

    public static Location convertLatLngToLocation(LatLng latLng){
        Location location = new Location("");
        location.setLongitude(latLng.longitude);
        location.setLatitude(latLng.latitude);
        return location;
    }
}
