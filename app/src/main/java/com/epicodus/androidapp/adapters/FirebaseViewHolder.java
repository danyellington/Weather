package com.epicodus.androidapp.adapters;


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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    View mView;
    Context mContext;

    public FirebaseViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindForecast(Forecast forecast) {
//        ImageView forecastImage = (ImageView) mView.findViewById(R.id.image);
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
    public void onClick(View view) {
        final ArrayList<Forecast> forecasts = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_LOCATION);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    forecasts.add(snapshot.getValue(Forecast.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, LocalActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("forecasts", Parcels.wrap(forecasts));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}


