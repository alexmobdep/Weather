package com.example.alex.largo.weather.ui.main_screen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.alex.largo.weather.R;
import com.example.alex.largo.weather.model.open_weather.OpenWeatherResponse;
import com.example.alex.largo.weather.ui.add_new_card.TabbedActivity;
import com.example.alex.largo.weather.ui.main_screen.impl.WeatherPresenterImpl;

public class WeatherActivity extends AppCompatActivity implements WeatherView {

    private static final int REQUEST_CODE = 200;
    public static final String EXTRA_LOCATION_DATA = "location_data_from_tabbed_activity";

    private LocationManager mLocationManager;
    private LocationListener mLocationListener;

    private WeatherPresenter mWeatherPresenter;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initLocationTools();
        makeRequest();
        mWeatherPresenter = new WeatherPresenterImpl(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makeRequest();
                }
        }
    }

    private void initLocationTools(){
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mWeatherPresenter.getWeatherInfo(location);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        };
    }

    private void makeRequest() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET}, REQUEST_CODE);
            }
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,
                0, mLocationListener);


    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(WeatherActivity.this, TabbedActivity.class), REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            if (data.getParcelableExtra(EXTRA_LOCATION_DATA) != null){
                Location location = data.getParcelableExtra(EXTRA_LOCATION_DATA);
                mWeatherPresenter.getWeatherInfo(location);
            }else if (data.getStringExtra(EXTRA_LOCATION_DATA) != null){
                String cityName = data.getStringExtra(EXTRA_LOCATION_DATA);
                mWeatherPresenter.getWeatherInfo(cityName);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeatherPresenter.onDestroy();
    }

    @Override
    public void showErrorDialog() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void inflateData(OpenWeatherResponse response) {

    }
}
