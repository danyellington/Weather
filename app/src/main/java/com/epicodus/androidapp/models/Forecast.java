package com.epicodus.androidapp.models;

import android.support.annotation.NonNull;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.Collection;

@Parcel
public class Forecast {

    String temperature;
    String temperatureMin;
    String humidity;
    String precipitation;
    String wind;
    String date;
    String city;
    String pushId;
    String index;

    public Forecast() {
    }

    public Forecast(String temperature, String temperatureMin, String humidity, String precipitation, String wind, String date, String city) {
        this.temperature = temperature;
        this.temperatureMin = temperatureMin;
        this.humidity = humidity;
        this.precipitation = precipitation;
        this.wind = wind;
        this.date = date;
        this.city = city;
        this.pushId = pushId;
        this.index = "not_specified";
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(String temperatureMin) {
        this.temperatureMin = temperatureMin;
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

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getWind() {return wind;}

    public void setWind(String wind) {this.wind = wind;}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getIndex() { return index; }

    public void setIndex(String s) { this.index = index; }
}