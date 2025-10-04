package com.eliftekin.weatherapp.repository

import com.eliftekin.weatherapp.BuildConfig
import com.eliftekin.weatherapp.models.apimodels.DailyDataModel
import com.eliftekin.weatherapp.models.apimodels.HourlyDataModel
import com.eliftekin.weatherapp.models.apimodels.WeatherDataModel
import com.eliftekin.weatherapp.services.RetrofitInstance

class WeatherRepo {
    private val currentWeatherApi = RetrofitInstance.weatherService
    private val locationApi = RetrofitInstance.locationService
    private val hourlyApi = RetrofitInstance.hourlyService
    private val dailyApi = RetrofitInstance.dailyService

    private val key = BuildConfig.API_KEY

    suspend fun getWeather(lat: Double, lon: Double): WeatherDataModel{
        return currentWeatherApi.getWeather(lat, lon, "metric", key)
    }

    suspend fun getLocation(lat: Double, lon: Double): String{
        val response = locationApi.getLocation(lat, lon, key)
        return response.firstOrNull()?.name ?: "UNKNOWN"
    }

    suspend fun getHourly(lat: Double, lon: Double): HourlyDataModel{
        return hourlyApi.getHourly(lat, lon, "metric", 12, key)
    }

    suspend fun getDaily(lat: Double, lon: Double): DailyDataModel {
        return dailyApi.getDaily(lat, lon, 10, "metric", key)
    }
}