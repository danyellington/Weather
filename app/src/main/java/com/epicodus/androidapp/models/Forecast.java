package com.epicodus.androidapp.models;

import org.parceler.Parcel;


@Parcel
public class Forecast {
    Double maxTemperature;
    Double minTemperature;
    Double avgTemperature;
    String condition;
    String conditionIcon;
    String windDirection;
    Double precipitation;
    Double humidity;
    Double visibility;


    public Forecast() {
    }

    public Forecast(Double maxTemperature, Double minTemperature, Double avgTemperature, String condition, String conditionIcon, String windDirection, Double precipitation, Double humidity, Double visibility) {
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
        this.avgTemperature = avgTemperature;
        this.condition = condition;
        this.conditionIcon = conditionIcon;
        this.windDirection = windDirection;
        this.precipitation = precipitation;
        this.humidity = humidity;
        this.visibility = visibility;
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

    public Double getAvgTemperature() {
        return avgTemperature;
    }

    public void setAvgTemperature(Double avgTemperature) {
        this.avgTemperature = avgTemperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getConditionIcon() {
        return conditionIcon;
    }

    public void setConditionIcon(String conditionIcon) {
        this.conditionIcon = conditionIcon;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
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

    public Double getVisibility() {
        return visibility;
    }

    public void setVisibility(Double visibility) {
        this.visibility = visibility;
    }
}

