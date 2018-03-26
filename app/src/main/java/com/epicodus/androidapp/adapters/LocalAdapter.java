package com.epicodus.androidapp.adapters;

import java.util.ArrayList;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.androidapp.R;
import com.epicodus.androidapp.models.Forecast;
import com.epicodus.androidapp.ui.LocalActivity;
import com.epicodus.androidapp.ui.LocalActivity;

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

            @BindView(R.id.name)
            TextView mName;
            @BindView(R.id.max) TextView mMax;
            @BindView(R.id.humidity) TextView mHumidity;
            @BindView(R.id.precipitation) TextView mPrecipitation;

            private Context mContext;

            public ForecastViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);

                mContext = itemView.getContext();
                itemView.setOnClickListener(this);
            }

            public void bindForecast(Forecast forecast) {
                mMax.setText("Temp(F): " + forecast.getTemperature());
                mHumidity.setText("Humidity(%): " + forecast.getHumidity());
                mPrecipitation.setText("Precipitation(in.): " + forecast.getPrecipitation());


            }

            @Override
            public void onClick(View v) {
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, LocalActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("location", Parcels.wrap(mForecasts));
                mContext.startActivity(intent);
            }
        }
    }