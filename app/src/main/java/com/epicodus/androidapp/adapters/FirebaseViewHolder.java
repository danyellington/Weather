package com.epicodus.androidapp.adapters;


import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.androidapp.Constants;
import com.epicodus.androidapp.R;
import com.epicodus.androidapp.models.Forecast;
import com.epicodus.androidapp.ui.LocalActivity;
import com.epicodus.androidapp.util.ItemTouchHelperViewHolder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    View mView;
    Context mContext;

    public TextView mDate;

    public FirebaseViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();

    }

    public void bindForecast(Forecast forecast) {
        mDate = (TextView) mView.findViewById(R.id.date);
       // ImageView forecastImage = (ImageView) mView.findViewById(R.id.image);
        TextView city = (TextView) mView.findViewById(R.id.name);
        TextView date = (TextView) mView.findViewById(R.id.date);
        TextView temp = (TextView) mView.findViewById(R.id.max);
        //TextView humidity = (TextView) mView.findViewById(R.id.humidity);
        TextView tempMin = (TextView) mView.findViewById(R.id.min);
      //  TextView precipitation = (TextView) mView.findViewById(R.id.precipitation);

//        Picasso.with(mContext)
//                .load(forecast.getImage())
//                //.resize(MAX_WIDTH, MAX_HEIGHT)
//                .centerCrop()
//                .into(forecastImage);


        city.setText(forecast.getCity());
        date.setText(forecast.getDate());
        temp.setText("High: " + forecast.getTemperature() + " F");
        tempMin.setText("Low: " + forecast.getTemperatureMin() + " F");
       // humidity.setText(forecast.getHumidity());
       // precipitation.setText(forecast.getPrecipitation());
    }


    @Override
    public void onItemSelected() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_on);
        set.setTarget(itemView);
        set.start();
    }

    @Override
    public void onItemClear() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext,
                R.animator.drag_scale_off);
        set.setTarget(itemView);
        set.start();
    }
}


