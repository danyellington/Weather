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
//            String jsonCityData = response.body().string();
//            JSONObject weatherCityJSON = new JSONObject(jsonCityData);
//            JSONObject forecastCityJSON = weatherCityJSON.getJSONObject("location");
//            String name = forecastDayJSON.getString("name");
//            Log.d("city", name);

            String jsonData = response.body().string();
            JSONObject weatherJSON = new JSONObject(jsonData);
            JSONObject forecastJSON = weatherJSON.getJSONObject("current");
            String temperature = forecastJSON.getString("temp_f");
            String humidity = forecastJSON.getString("humidity");
            String precipitation = forecastJSON.getString("precip_mm");
            String image = forecastJSON.getJSONObject("condition").getString("icon");
            Log.d("image" , image);
            Forecast forecast = new Forecast(temperature, humidity, precipitation, image);
            forecasts.add(forecast);
            return forecasts;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return forecasts;
    }

}
