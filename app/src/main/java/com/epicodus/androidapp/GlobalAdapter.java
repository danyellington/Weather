package com.epicodus.androidapp;


import android.content.Context;
import android.widget.ArrayAdapter;

public class GlobalAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mCities;


    public GlobalAdapter(Context mContext, int resource, String[] mCities) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mCities = mCities;

    }

    @Override
    public Object getItem(int position) {
        String cities = mCities[position];

        return String.format("%s \n %s", cities);
    }

    @Override
    public int getCount() {
        return mCities.length;
    }
}