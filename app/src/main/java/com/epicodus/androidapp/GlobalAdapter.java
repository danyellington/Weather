package com.epicodus.androidapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

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
