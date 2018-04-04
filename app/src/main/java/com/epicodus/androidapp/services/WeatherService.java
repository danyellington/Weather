package com.epicodus.androidapp.services;

import android.util.Log;

import com.epicodus.androidapp.Constants;
import com.epicodus.androidapp.models.Forecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class WeatherService {
        public static void findForecast(String location, Callback callback) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .build();

            HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WEATHER_KEY_BASE_URL).newBuilder();
            urlBuilder.addQueryParameter("key", Constants.WEATHER_KEY);
            urlBuilder.addQueryParameter(Constants.YOUR_QUERY_PARAMETER, location);
            urlBuilder.addQueryParameter("days", Constants.WEATHER_FORECAST);
            String url = urlBuilder.build().toString();


            Request request= new Request.Builder()
                    .url(url)
                    .build();

            Call call = client.newCall(request);
            call.enqueue(callback);

        }


    public ArrayList<Forecast> processResults(Response response) {
        ArrayList<Forecast> forecasts = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject weatherJSON = new JSONObject(jsonData);
            JSONObject locationJSON = weatherJSON.getJSONObject("location");
            String city = locationJSON.getString("name");
            JSONObject forecastJSON = weatherJSON.getJSONObject("forecast");
            JSONArray forecastDayJSON = forecastJSON.getJSONArray("forecastday");
            for (int i = 0; i < forecastDayJSON.length(); i++) {
                JSONObject dateJSON = forecastDayJSON.getJSONObject(i);
                String date = dateJSON.getString("date");
                JSONObject dayJSON = dateJSON.getJSONObject("day");
                String temperature = dayJSON.getString("maxtemp_f");
                String temperatureMin = dayJSON.getString("mintemp_f");
                String humidity = dayJSON.getString("avghumidity");
                String precipitation = dayJSON.getString("totalprecip_mm");
                String wind = dayJSON.getString("maxwind_mph");
                String image = dayJSON.getJSONObject("condition").getString("icon");


                Forecast forecast = new Forecast(temperature, temperatureMin, humidity, precipitation, wind, city, date);
                forecasts.add(forecast);


            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return forecasts;
    }


}
