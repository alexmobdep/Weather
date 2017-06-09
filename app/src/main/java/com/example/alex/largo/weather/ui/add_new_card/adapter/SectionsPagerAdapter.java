package com.example.alex.largo.weather.ui.add_new_card.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.alex.largo.weather.ui.add_new_card.fragments.GoogleMapsFragment;
import com.example.alex.largo.weather.ui.add_new_card.fragments.PickCityFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private static final int PAGE_NUM = 2;

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new GoogleMapsFragment();
            case 1:
                return new PickCityFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_NUM;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "MAP";
            case 1:
                return "LIST";
        }
        return null;
    }
}

