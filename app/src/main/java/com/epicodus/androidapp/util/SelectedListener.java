package com.epicodus.androidapp.util;


import com.epicodus.androidapp.models.Forecast;

import java.util.ArrayList;

public interface SelectedListener {
    public void selected(Integer position, ArrayList<Forecast> forecasts, String source);
}
