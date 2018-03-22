package com.epicodus.androidapp.services;
import com.epicodus.androidapp.Constants;
import com.epicodus.androidapp.models.Forecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
        urlBuilder.addQueryParameter(Constants.YOUR_QUERY_PARAMETER, location);
        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .header("Authorization", Constants.WEATHER_KEY + "&q=")
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public Forecast processResult(Response response) {
        Forecast forecast;
        try {
            String jsonData = response.body().string();
            JSONObject weatherJSON = new JSONObject(jsonData);
            JSONObject forecastJSON = weatherJSON.getJSONObject("current");
            Double maxTemperature = forecastJSON.getDouble("maxtemp_f");
            Double minTemperature = forecastJSON.getDouble("mintemp_f");
            Double avgTemperature = forecastJSON.getDouble("avgtemp_f");
            JSONObject condition = forecastJSON.getJSONObject("condition");
            String conditionString = condition.getString("text");
            String conditionIcon = condition.getString("icon");
            String windDirection = forecastJSON.getString("maxwind_mph");
            Double precipitation = forecastJSON.getDouble("totalprecip_in");
            Double humidity = forecastJSON.getDouble("avghumidity");
            Double visibility = forecastJSON.getDouble("avgvis_miles");
            forecast = new Forecast(maxTemperature, minTemperature, avgTemperature, conditionString, conditionIcon, windDirection, precipitation, humidity, visibility);
            return forecast;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
