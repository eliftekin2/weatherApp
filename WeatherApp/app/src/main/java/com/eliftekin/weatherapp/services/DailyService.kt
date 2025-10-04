package com.eliftekin.weatherapp.services

import com.eliftekin.weatherapp.models.apimodels.DailyDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface DailyService {
    @GET("data/2.5/forecast/daily")
    suspend fun getDaily(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("cnt") cnt: Int,
        @Query("units") units: String,
        @Query("appid") appid: String,
    ) : DailyDataModel
}