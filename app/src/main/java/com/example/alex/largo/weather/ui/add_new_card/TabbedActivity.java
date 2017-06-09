package com.example.alex.largo.weather.ui.add_new_card;

import android.content.Intent;
import android.location.Location;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.alex.largo.weather.R;
import com.example.alex.largo.weather.ui.add_new_card.adapter.SectionsPagerAdapter;
import com.example.alex.largo.weather.ui.main_screen.WeatherActivity;
import com.example.alex.largo.weather.utils.LocationUtils;
import com.google.android.gms.maps.model.LatLng;

public class TabbedActivity extends AppCompatActivity implements OnDataPass {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);
        initViews();
    }

    private void initViews(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
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
