package com.epicodus.androidapp.models;

import android.support.annotation.NonNull;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.Collection;


public class Forecast extends ArrayList<Forecast> {
    String temperature;
    String precipitation;
    String humidity;

    public Forecast(String temperature, String precipitation, String humidity) {

        this.temperature = temperature;
        this.precipitation = precipitation;
        this.humidity = humidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}