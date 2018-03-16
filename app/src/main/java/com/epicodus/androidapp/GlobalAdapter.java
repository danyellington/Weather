package com.epicodus.androidapp;


import android.content.Context;
import android.widget.ArrayAdapter;

public class GlobalAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mCities;
    private String[] mTemps;

    public GlobalAdapter(Context mContext, int resource, String[] mCities, String[] mTemps) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mCities = mCities;
        this.mTemps = mTemps;
    }

    @Override
    public Object getItem(int position) {
        String cities = mCities[position];
        String temps = mTemps[position];
        return String.format("%s \n %s", cities, temps);
    }

    @Override
    public int getCount() {
        return mCities.length;
    }
}