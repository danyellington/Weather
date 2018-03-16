package com.epicodus.androidapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlobalActivity extends AppCompatActivity {
    @BindView(R.id.globalTextView) TextView mGlobalTextView;
    @BindView(R.id.globalListView) ListView mGlobalListView;

    private String[] cities = new String[]{"Portland", "New York", "Tokyo", "London", "Paris", "Berlin", "Dubai", "Rio", "Toronto", "Reykjavik", "Cairo", "Beijing", "Delhi"

    };

    private String[] temps = new String[] {"Portland", "New York", "Tokyo", "London", "Paris", "Berlin", "Dubai", "Rio", "Toronto", "Reykjavik", "Cairo", "Beijing", "Delhi"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);
        ButterKnife.bind(this);

        GlobalAdapter adapter = new GlobalAdapter(this, android.R.layout.simple_list_item_1, cities, temps);
        mGlobalListView.setAdapter(adapter);

        mGlobalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String cities = ((TextView)view).getText().toString();
                Toast.makeText(GlobalActivity.this, cities, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        mGlobalTextView.setText("Here: " + location);
    }
}