package com.epicodus.androidapp.models;

import android.support.annotation.NonNull;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.Collection;

@Parcel
public class Forecast extends ArrayList<Forecast> {
    String temperature;
    String humidity;
    String precipitation;
    String image;
    String pushId;

    public Forecast() {}

    public Forecast(String temperature, String humidity, String precipitation, String image) {

        this.temperature = temperature;
        this.humidity = humidity;
        this.precipitation = precipitation;
        this.image = image;

    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public String getImage() {return image;}

    public void setImage(String image) {this.image = image;}

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }


    }
