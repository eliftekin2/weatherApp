package com.eliftekin.weatherapp.services

import com.eliftekin.weatherapp.models.apimodels.HourlyDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface HourlyService {
    @GET("data/2.5/forecast/hourly")
    suspend fun getHourly(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("cnt") cnt: Int,
        @Query("appid") appid: String,
    ): HourlyDataModel
}