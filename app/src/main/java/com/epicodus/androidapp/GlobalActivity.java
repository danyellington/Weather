package com.epicodus.androidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GlobalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);
    }
}
