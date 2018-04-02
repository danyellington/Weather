package com.epicodus.androidapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.epicodus.androidapp.R;
import com.epicodus.androidapp.adapters.LocalAdapter;
import com.epicodus.androidapp.models.Forecast;
import com.epicodus.androidapp.services.WeatherService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class LocalActivity extends AppCompatActivity {
    public static final String TAG = LocalActivity.class.getSimpleName();
//    @BindView(R.id.locationTextView)
//    TextView mLocationTextView;

//    @BindView(R.id.goToRadarButton)
//    ImageView mGoToRadarButton;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private LocalAdapter mAdapter;

    public ArrayList<Forecast> forecasts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        Log.d("wtf", location);
        getForecast(location);


//        ImageView Button = (ImageView) findViewById(R.id.goToRadarButton);
//        Button.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.addCategory(Intent.CATEGORY_BROWSABLE);
//                intent.setData(Uri.parse("https://radar.weather.gov"));
//                startActivity(intent);
//            }
//
//
//        });

    }

    private void getForecast(String location) {
        final WeatherService weatherService = new WeatherService();

        weatherService.findForecast(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                forecasts = weatherService.processResults(response);
                LocalActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new LocalAdapter(getApplicationContext(), forecasts);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(LocalActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });


            }
        });


    }
}

