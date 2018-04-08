package com.epicodus.androidapp.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.androidapp.models.Forecast;
import com.epicodus.androidapp.ui.LocationFragment;

import java.util.ArrayList;

public class LocalPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Forecast> mForecasts;

    public LocalPagerAdapter(FragmentManager fm, ArrayList<Forecast> forecasts) {
        super(fm);
        mForecasts = forecasts;
    }

    @Override
    public Fragment getItem(int position) {
        return LocationFragment.newInstance(mForecasts, position);
    }

    @Override
    public int getCount() {
        return mForecasts.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mForecasts.get(position).getCity();
    }
}