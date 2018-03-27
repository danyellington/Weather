package com.epicodus.androidapp.ui;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.epicodus.androidapp.R;
import com.epicodus.androidapp.adapters.LocalAdapter;
import com.epicodus.androidapp.models.Forecast;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyActivity extends AppCompatActivity {

    private LocalAdapter adapterViewPager;
    ArrayList<Forecast> mDailyForecast = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        getForecast(location);
    }

    private void getForecast(String location) {
    }
}
