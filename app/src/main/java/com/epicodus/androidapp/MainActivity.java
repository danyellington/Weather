package com.epicodus.androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.locationButton)
    Button mEnterButton;
    @BindView(R.id.locationEditText)
    EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);
        mEnterButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String location = mLocationEditText.getText().toString();
        Intent intent = new Intent(MainActivity.this, LocalActivity.class);
        intent.putExtra("location", location);
        startActivity(intent);
    }


}



