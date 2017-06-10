package com.example.alex.largo.weather.ui.add_new_card.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.alex.largo.weather.R;
import com.example.alex.largo.weather.ui.add_new_card.OnDataPass;
import com.example.alex.largo.weather.utils.LocationUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

public class GoogleMapsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private OnDataPass mDataPasser;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mDataPasser = (OnDataPass) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_google_maps, container, false);
        initMap();
        return view;
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                MarkerOptions marker = new MarkerOptions().position(
                        new LatLng(latLng.latitude, latLng.longitude));
                mMap.addMarker(marker);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                try {
                    String cityName = LocationUtils.convertLatLngToCityName(latLng, getContext());
                    showDialog(cityName, latLng, mMap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showDialog(String cityName, final LatLng latLng, final GoogleMap googleMap){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.city_choice) + cityName)
                .setPositiveButton(R.string.dialog_ok_btn_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDataPasser.passData(latLng);
                    }
                })
                .setNegativeButton(R.string.dialog_cancel_btn_text, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        googleMap.clear();
                    }
                }).create()
                .show();
    }
}
