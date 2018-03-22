package com.epicodus.androidapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LocalActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.locationTextView)
    TextView mLocationTextView;
    @BindView(R.id.textView4)
    TextView mTextView4;
    @BindView(R.id.listView)
    ListView mListView;
    @BindView(R.id.radarButton)
    Button mRadarButton;


    private String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    private String[] nights = new String[]{"High/Low:", "High/Low:", "High/Low:", "High/Low:", "High/Low:", "High/Low:", "High/Low:",};

    private void getWeather(String location) {
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
        LocalAdapter adapter = new LocalAdapter(this, android.R.layout.simple_list_item_1, days, nights);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {



            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position>=0){
                    Intent i=new Intent(LocalActivity.this, DailyActivity.class);
                    startActivity(i);

                }
            }
        });




        }



    public void onClick(View view) {
        Intent intent = new Intent(LocalActivity.this, RadarActivity.class);
        startActivity(intent);

    }}

