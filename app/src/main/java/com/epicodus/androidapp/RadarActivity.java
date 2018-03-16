package com.epicodus.androidapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RadarActivity extends AppCompatActivity {
    @BindView(R.id.goToRadarButton)
    ImageView mGoToRadarButton;
    @BindView(R.id.radarTextView)
    TextView mRadarTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar);
        ButterKnife.bind(this);


        ImageView Button = (ImageView) findViewById(R.id.goToRadarButton);
        Button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://radar.weather.gov/ridge/radar_lite.php?rid=RTX&product=n0r&loop=yes"));
                startActivity(intent);

            }

        });
    }
}


