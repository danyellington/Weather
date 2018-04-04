package com.epicodus.androidapp.adapters;

import java.util.ArrayList;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.androidapp.R;
import com.epicodus.androidapp.models.Forecast;
import com.epicodus.androidapp.ui.LocalDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.ForecastViewHolder>{

        private ArrayList<Forecast> mForecasts = new ArrayList<>();
        private Context mContext;

        public LocalAdapter(Context context, ArrayList<Forecast> forecast) {
            mContext = context;
            mForecasts = forecast;
        }

        @Override
        public LocalAdapter.ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_local_list, parent, false);
            ForecastViewHolder viewHolder = new ForecastViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(LocalAdapter.ForecastViewHolder holder, int position) {
            holder.bindForecast(mForecasts.get(position));
        }

        @Override
        public int getItemCount() {
            return mForecasts.size();
        }


        public class ForecastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

//            @BindView(R.id.image)
//            ImageView mImageView;
            @BindView(R.id.name)
            TextView mName;
            @BindView(R.id.date)
            TextView mDate;
            @BindView(R.id.max) TextView mMax;
            @BindView(R.id.min) TextView mMin;

            private Context mContext;

            public ForecastViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);

                mContext = itemView.getContext();
                itemView.setOnClickListener(this);
            }

            public void bindForecast(Forecast forecast) {
               // Picasso.with(mContext).load(forecast.getImage()).into(mImageView);
                mMax.setText("High: " + forecast.getTemperature() + " F");
                mMin.setText("Low: " + forecast.getTemperatureMin() + " F");
//                mHumidity.setText("Avg Humidity: " + forecast.getHumidity() + "%");
//                mPrecipitation.setText("Total Precip: " + forecast.getPrecipitation() + " mm");
                mName.setText(forecast.getCity());
                mDate.setText(forecast.getDate());



            }

            @Override

            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, LocalDetailActivity.class);
                intent.putExtra("position", itemPosition);
                intent.putExtra("forecasts", Parcels.wrap(mForecasts));
                mContext.startActivity(intent);
            }
        }
    }