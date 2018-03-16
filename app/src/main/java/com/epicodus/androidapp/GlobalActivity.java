package com.epicodus.androidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;

public class GlobalActivity extends AppCompatActivity {
    @BindView(R.id.globalTextView) TextView mGlobalTextView;
    @BindView(R.id.globalListView) ListView mGlobalListView;

    private String[] cities = new String[]{"Portland", "New York", "Tokyo", "London", "Paris", "Berlin", "Dubai", "Rio", "Toronto", "Reykjavik", "Cairo", "Beijing", "Delhi"

    };


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        }
    }








