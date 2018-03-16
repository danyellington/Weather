package com.epicodus.androidapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.locationButton)
    Button mLocationButton;
    @BindView(R.id.globalButton)
   ImageView mGlobalButton;
    @BindView(R.id.locationEditText)
    EditText mLocationEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface rubikFont = Typeface.createFromAsset(getAssets(), "fonts/rubik.ttf");
//        mAppNameTextView.setTypeface(rubikFont);

        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, LocalActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });

        ImageView Button = (ImageView) findViewById(R.id.globalButton);
        Button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://www.accuweather.com/en/world-weather"));
                startActivity(intent);

            }

        });

    }
    }




