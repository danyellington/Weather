package com.epicodus.androidapp.models;

import org.parceler.Parcel;

import java.util.ArrayList;


public class Forecast extends ArrayList<Forecast> {
    Double maxTemperature;
    Double minTemperature;
    Double precipitation;
    Double humidity;
    String name;


//    public Forecast() {}



    public Forecast(Double maxTemperature, Double minTemperature, Double precipitation, Double humidity, String name) {
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.precipitation = precipitation;
        this.humidity = humidity;
        this.name = name;
    }



    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Double precipitation) {
        this.precipitation = precipitation;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

