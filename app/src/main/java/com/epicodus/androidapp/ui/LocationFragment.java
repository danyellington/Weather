package com.epicodus.androidapp.ui;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.androidapp.Constants;
import com.epicodus.androidapp.R;
import com.epicodus.androidapp.models.Forecast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.date)
    TextView mDate;
    @BindView(R.id.max)
    TextView mTemp;
    @BindView(R.id.min) TextView mMin;
    @BindView(R.id.humidity)
    TextView mHumidity;
    @BindView(R.id.avgPrecipitation)
    TextView mPrecipitation;
    @BindView(R.id.wind) TextView mWind;
    @BindView(R.id.saveLocationButton)
    TextView mSavedLocationsButton;
    private int mPosition;
    private Forecast mForecast;
    private ArrayList<Forecast> mForecasts;

    public static LocationFragment newInstance (ArrayList<Forecast> forecasts, Integer position) {
        LocationFragment locationFragment = new LocationFragment();
        Bundle args = new Bundle();

        args.putParcelable(Constants.EXTRA_KEY_FORECASTS, Parcels.wrap(forecasts));
        args.putInt(Constants.EXTRA_KEY_POSITION, position);

        locationFragment.setArguments(args);
        return locationFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mForecasts = Parcels.unwrap(getArguments().getParcelable(Constants.EXTRA_KEY_FORECASTS));
        mPosition = getArguments().getInt(Constants.EXTRA_KEY_POSITION);
        mForecast = mForecasts.get(mPosition);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location, container, false);
        ButterKnife.bind(this, view);

//        Picasso.with(view.getContext())
//                .load(mForecast.getImage())
//                //.resize(MAX_WIDTH, MAX_HEIGHT)
//                .centerCrop()
//                .into(mImage);
        mName.setText(mForecast.getCity());
        mDate.setText(mForecast.getDate());
        mTemp.setText("High: " + mForecast.getTemperature() + " F");
        mMin.setText("Low: " + mForecast.getTemperatureMin() + " F");
        mHumidity.setText("Avg Humidity: " + mForecast.getHumidity() + "%");
        mPrecipitation.setText("Total Precip: " + mForecast.getPrecipitation() + "mm");
        mWind.setText("Wind: " + mForecast.getWind() + " MPH");

        mSavedLocationsButton.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View v) {

        if (v == mSavedLocationsButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference forecastRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_LOCATION)
                    .child(uid);

            DatabaseReference pushRef = forecastRef.push();
            String pushId = pushRef.getKey();
            mForecast.setPushId(pushId);
            pushRef.setValue(mForecast);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }

    }


}



