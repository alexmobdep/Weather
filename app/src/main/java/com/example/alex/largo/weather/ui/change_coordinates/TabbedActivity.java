package com.example.alex.largo.weather.ui.change_coordinates;

import android.content.Intent;
import android.location.Location;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.alex.largo.weather.R;
import com.example.alex.largo.weather.ui.change_coordinates.adapter.SectionsPagerAdapter;
import com.example.alex.largo.weather.ui.main_screen.WeatherActivity;
import com.example.alex.largo.weather.utils.LocationUtils;
import com.google.android.gms.maps.model.LatLng;

public class TabbedActivity extends AppCompatActivity implements OnDataPass {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);
        initViews();
    }

    private void initViews(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void passData(String cityName) {
        Intent intent = new Intent();
        intent.putExtra(WeatherActivity.EXTRA_LOCATION_DATA, cityName);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void passData(LatLng latLng) {
        Location location = LocationUtils.convertLatLngToLocation(latLng);
        Intent intent = new Intent();
        intent.putExtra(WeatherActivity.EXTRA_LOCATION_DATA, location);
        setResult(RESULT_OK, intent);
        finish();
    }

}
