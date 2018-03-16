package com.epicodus.androidapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.locationButton)
    Button mLocationButton;
    @BindView(R.id.globalButton)
    Button mGlobalButton;
    @BindView(R.id.locationEditText)
    EditText mLocationEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        Typeface amaticFont = Typeface.createFromAsset(getAssets(), "fonts/Amatic.ttf");
//        mAppNameTextView.setTypeface(amaticFont);

        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = mLocationEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, LocalActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);
            }
        });

        mGlobalButton = (Button) findViewById(R.id.globalButton);
        mGlobalButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GlobalActivity.class);

                startActivity(intent);
            }

        });
    }

}


//    @BindView(R.id.locationButton)
//    Button mLocationButton;
//    @BindView(R.id.globalButton)
//    Button mGlobalButton;
//    @BindView(R.id.locationEditText)
//    EditText mLocationEditText;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//        mLocationButton.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View view) {
//                String location = mLocationEditText.getText().toString();
//                Intent intent = new Intent(MainActivity.this, LocalActivity.class);
//                intent.putExtra("location", location);
//                startActivity(intent);
//            }
//
//
//        });
//        mGlobalButton = (Button) findViewById(R.id.globalButton);
//        mGlobalButton.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, GlobalActivity.class);
//                startActivity(intent);
//            }
//
//        });
//    }
//
//
//}
//
//
