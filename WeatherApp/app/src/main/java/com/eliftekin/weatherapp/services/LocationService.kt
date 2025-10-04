package com.eliftekin.weatherapp.services

import com.eliftekin.weatherapp.models.apimodels.LocationDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {
    @GET("geo/1.0/reverse")
    suspend fun getLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") key: String
    ) : List<LocationDataModel>
}