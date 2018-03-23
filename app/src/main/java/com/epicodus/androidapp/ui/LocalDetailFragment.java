package com.epicodus.androidapp.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.androidapp.R;
import com.epicodus.androidapp.models.Forecast;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LocalDetailFragment extends Fragment {
        @BindView(R.id.max)
        ImageView mMax;
        @BindView(R.id.min)
        TextView mMin;
        @BindView(R.id.humidity)
        TextView mHumidity;
        @BindView(R.id.name)
        TextView mName;


        private Forecast mForecast;

        public static LocalDetailFragment newInstance(Forecast forecast) {
            LocalDetailFragment localDetailFragment = new LocalDetailFragment();
            Bundle args = new Bundle();
            args.putParcelable("text", Parcels.wrap(forecast));
            localDetailFragment.setArguments(args);
            return localDetailFragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mForecast = Parcels.unwrap(getArguments().getParcelable("text"));
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_local_detail, container, false);
            ButterKnife.bind(this, view);

           // Picasso.with(view.getContext()).load(mForecast.getImageUrl()).into(mImageLabel);

            mName.setText(mForecast.getName());

//            mMax.setDouble(mForecast.getMaxTemperature());
//            mMin.setText(mForecast.getMinTemperature());
//            mHumidity.setText(mForecast.getHumidity());

           // mRadarButton.setOnClickListener(this);


            return view;
        }


}
