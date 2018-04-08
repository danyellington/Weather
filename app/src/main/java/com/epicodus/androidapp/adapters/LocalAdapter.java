package com.epicodus.androidapp.adapters;

import java.util.ArrayList;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.androidapp.Constants;
import com.epicodus.androidapp.R;
import com.epicodus.androidapp.models.Forecast;
import com.epicodus.androidapp.ui.LocalDetailActivity;
import com.epicodus.androidapp.ui.LocationFragment;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.ForecastViewHolder> {

    private ArrayList<Forecast> mForecasts = new ArrayList<>();
    private Context mContext;

    public LocalAdapter(Context context, ArrayList<Forecast> forecast) {
        mContext = context;
        mForecasts = forecast;
    }

    @Override
    public LocalAdapter.ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.local_list_item, parent, false);
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
        @BindView(R.id.max)
        TextView mMax;
        @BindView(R.id.min)
        TextView mMin;

        private Context mContext;
        private int mOrientation;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            itemView.setOnClickListener(this);

            mOrientation = itemView.getResources().getConfiguration().orientation;
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }
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
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, LocalDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_FORECASTS, Parcels.wrap(mForecasts));
                mContext.startActivity(intent);
            }
        }

        private void createDetailFragment(int position) {
            LocationFragment detailFragment = LocationFragment.newInstance(mForecasts, position);
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.restaurantDetailContainer, detailFragment);
            ft.commit();
        }

    }
}