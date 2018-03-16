package com.epicodus.androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocalActivity extends AppCompatActivity implements View.OnClickListener{
    @BindView(R.id.locationTextView)
    TextView mLocationTextView;
    @BindView(R.id.listView)
    ListView mListView;
    @BindView(R.id.radarButton)
    Button mRadarButton;


    private String[] days = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    private String[] nights = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        ButterKnife.bind(this);
        mRadarButton.setOnClickListener(this);
        LocalAdapter adapter = new LocalAdapter(this, android.R.layout.simple_list_item_1, days, nights);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {



            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String days = ((TextView) view).getText().toString();
                Toast.makeText(LocalActivity.this, days, Toast.LENGTH_LONG).show();
                Intent intent = getIntent();
                String location = intent.getStringExtra("location");
                mLocationTextView.setText("Weekly Forecast for: " + location);

            }

        });




        }



    public void onClick(View view) {
        Intent intent = new Intent(LocalActivity.this, RadarActivity.class);
        startActivity(intent);

    }}

