package com.epicodus.androidapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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

public class LocalActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = LocalActivity.class.getSimpleName();
    @BindView(R.id.locationTextView)
    TextView mLocationTextView;
    @BindView(R.id.textView4)
    TextView mTextView4;
    @BindView(R.id.listView)
    ListView mListView;
    @BindView(R.id.radarButton)
    Button mRadarButton;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private LocalAdapter mAdapter;

    public ArrayList<Forecast> forecasts = new ArrayList<>();


    public void getForecast(String location) {
        final WeatherService weatherService = new WeatherService();

        weatherService.findForecast(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                Forecast forecast = weatherService.processResult(response);
                LocalActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new LocalAdapter(getApplicationContext(),forecasts);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        ButterKnife.bind(this);
        Intent in = getIntent();
        String tv1= in.getExtras().getString("location");
        mTextView4.setText(tv1);
        mRadarButton.setOnClickListener(this);

//        mListView.setAdapter(adapter);
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()

            getForecast("location");
        {



//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                if(position>=0){
//                    Intent i=new Intent(LocalActivity.this, DailyActivity.class);
//                    startActivity(i);
//
//                }
//            }
        }




        }



    public void onClick(View view) {
        Intent intent = new Intent(LocalActivity.this, RadarActivity.class);
        startActivity(intent);

    }}

