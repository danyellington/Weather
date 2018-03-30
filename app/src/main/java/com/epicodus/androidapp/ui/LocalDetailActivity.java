package com.epicodus.androidapp.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.androidapp.R;
import com.epicodus.androidapp.adapters.LocalPagerAdapter;
import com.epicodus.androidapp.models.Forecast;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocalDetailActivity extends AppCompatActivity {


        @BindView(R.id.viewPager) ViewPager mViewPager;
        private LocalPagerAdapter adapterViewPager;
        ArrayList<Forecast> mForecasts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_local_detail);
            ButterKnife.bind(this);

            mForecasts = Parcels.unwrap(getIntent().getParcelableExtra("location"));

            int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));

            adapterViewPager = new LocalPagerAdapter(getSupportFragmentManager(), mForecasts);
            mViewPager.setAdapter(adapterViewPager);
            mViewPager.setCurrentItem(startingPosition);
        }
    }
