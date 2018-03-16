package com.epicodus.androidapp;


import android.content.Context;
import android.widget.ArrayAdapter;

public class LocalAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mDays;
    private String[] mNights;

    public LocalAdapter(Context mContext, int resource, String[] mDays, String[] mNights) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mDays = mDays;
        this.mNights = mNights;
    }

    @Override
    public Object getItem(int position) {
        String days = mDays[position];
        String nights = mNights[position];
        return String.format("%s \n %s", days, nights);
    }

    @Override
    public int getCount() {
        return mDays.length;
    }
}