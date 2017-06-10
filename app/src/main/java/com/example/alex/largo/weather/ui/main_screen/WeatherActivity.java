package com.example.alex.largo.weather.ui.main_screen;

import android.Manifest;
import android.content.DialogInterface;
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
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.alex.largo.weather.R;
import com.example.alex.largo.weather.model.open_weather.OpenWeatherResponse;
import com.example.alex.largo.weather.ui.change_coordinates.TabbedActivity;
import com.example.alex.largo.weather.ui.main_screen.impl.WeatherPresenterImpl;

public class WeatherActivity extends AppCompatActivity implements WeatherView {

    private static final int REQUEST_CODE = 200;
    private static final int MIN_TIME_AND_DISTANCE = 0;

    public static final String EXTRA_LOCATION_DATA = "location_data_from_tabbed_activity";

    private LocationManager mLocationManager;
    private LocationListener mLocationListener;

    private WeatherPresenter mWeatherPresenter;

    private RelativeLayout mProgressLayout;
    private TextView mCountryName;
    private TextView mCityName;
    private TextView mPressureTextView;
    private TextView mHumidityTextView;
    private TextView mDescriptionTextView;
    private TextView mWindSpeedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initLocationTools();
        getCoordinates();
        mWeatherPresenter = new WeatherPresenterImpl(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCoordinates();
                }
        }
    }

    private void initLocationTools() {
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mWeatherPresenter.getWeatherInfo(location);
                mLocationManager.removeUpdates(mLocationListener);
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

    private void getCoordinates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.INTERNET}, REQUEST_CODE);
            }
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME_AND_DISTANCE,
                MIN_TIME_AND_DISTANCE, mLocationListener);


    }

    private void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mProgressLayout = (RelativeLayout) findViewById(R.id.progress_container);

        mCountryName = (TextView) findViewById(R.id.country_name_tv);
        mCityName = (TextView) findViewById(R.id.city_name_tv);
        mPressureTextView = (TextView) findViewById(R.id.pressure_tv);
        mHumidityTextView = (TextView) findViewById(R.id.humidity_tv);
        mDescriptionTextView = (TextView) findViewById(R.id.short_description_tv);
        mWindSpeedTextView = (TextView) findViewById(R.id.wind_speed_tv);

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
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.getParcelableExtra(EXTRA_LOCATION_DATA) != null) {
                Location location = data.getParcelableExtra(EXTRA_LOCATION_DATA);
                mWeatherPresenter.getWeatherInfo(location);
            } else if (data.getStringExtra(EXTRA_LOCATION_DATA) != null) {
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.error_dialog_text)
                .setPositiveButton(R.string.error_dialog_btn_close_app, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                }).create()
                .show();
    }

    @Override
    public void hideProgress() {
        mProgressLayout.setVisibility(View.GONE);
    }

    @Override
    public void inflateData(OpenWeatherResponse response) {
        String countryCode = response.getSys().getCountry();
        String cityName = response.getName();
        String pressure = String.valueOf(response.getMain().getPressure());
        String humidity = String.valueOf(response.getMain().getHumidity());
        String description = response.getWeatherList().get(0).getDescription();
        String windSpeed = String.valueOf(response.getWind().getSpeed());
        mCountryName.setText(countryCode);
        mCityName.setText(cityName);
        mPressureTextView.setText(pressure);
        mHumidityTextView.setText(humidity + getString(R.string.humidity_percent));
        mDescriptionTextView.setText(description);
        mWindSpeedTextView.setText(windSpeed);
    }
}
