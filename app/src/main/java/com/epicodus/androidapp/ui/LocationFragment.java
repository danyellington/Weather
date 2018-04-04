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

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationFragment extends Fragment implements View.OnClickListener {
//    @BindView(R.id.image)
//    ImageView mImage;
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

    private Forecast mForecast;

    public static LocationFragment newInstance(Forecast forecast) {
        LocationFragment locationFragment = new LocationFragment();
        Bundle args = new Bundle();
        args.putParcelable("location", Parcels.wrap(forecast));
        locationFragment.setArguments(args);
        return locationFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mForecast = Parcels.unwrap(getArguments().getParcelable("location"));
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

            DatabaseReference restaurantRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_LOCATION)
                    .child(uid);

            DatabaseReference pushRef = restaurantRef.push();
            String pushId = pushRef.getKey();
            mForecast.setPushId(pushId);
            pushRef.setValue(mForecast);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }

    }
}



