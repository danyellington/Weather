package com.epicodus.androidapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.androidapp.Constants;
import com.epicodus.androidapp.R;
import com.epicodus.androidapp.adapters.FirebaseViewHolder;
import com.epicodus.androidapp.models.Forecast;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedLocationsActivity extends AppCompatActivity {
    private DatabaseReference mForecastReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_local);
        ButterKnife.bind(this);

        mForecastReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_LOCATION);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Forecast, FirebaseViewHolder>
                (Forecast.class, R.layout.activity_local_list, FirebaseViewHolder.class,
                        mForecastReference) {

            @Override
            protected void populateViewHolder(FirebaseViewHolder viewHolder,
                                              Forecast model, int position) {
                viewHolder.bindForecast(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}