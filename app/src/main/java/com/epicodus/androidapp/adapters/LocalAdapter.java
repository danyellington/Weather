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
import com.epicodus.androidapp.ui.LocalDetailActivity;

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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.local_list_item_daily, parent, false);
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
            @BindView(R.id.min) TextView mMin;
            @BindView(R.id.humidity) TextView mHumidity;

            private Context mContext;

            public ForecastViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);

                mContext = itemView.getContext();
                itemView.setOnClickListener(this);
            }

            public void bindForecast(Forecast forecast) {

                mName.setText(forecast.getName());
//                mMax.setText(forecast.getMaxTemperature());
//                mMax.setText(forecast.getMinTemperature());
//                mMin.setText(forecast.getPrecipitation());
//                mHumidity.setText(forecast.getHumidity());


            }



            @Override
            public void onClick(View v) {
                Log.d("click listener", "working!");
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, LocalDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("location", Parcels.wrap(mForecasts));
                mContext.startActivity(intent);
            }
        }
    }