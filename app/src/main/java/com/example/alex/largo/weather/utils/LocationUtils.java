package com.example.alex.largo.weather.utils;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationUtils {
    private static final String PROVIDER = "";
    private static final int MAX_RESULT = 1;
    private static final int INDEX_ZERO = 0;


    public static Location convertLatLngToLocation(LatLng latLng){
        Location location = new Location(PROVIDER);
        location.setLongitude(latLng.longitude);
        location.setLatitude(latLng.latitude);
        return location;
    }

    public static String convertLatLngToCityName(LatLng latLng, Context context) throws IOException {
        Geocoder gcd = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = gcd.getFromLocation(latLng.latitude, latLng.longitude, MAX_RESULT);
        return addresses.get(INDEX_ZERO).getLocality();
    }
}
