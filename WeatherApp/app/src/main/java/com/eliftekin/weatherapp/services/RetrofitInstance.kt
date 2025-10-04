package com.eliftekin.weatherapp.services

import com.eliftekin.weatherapp.constants.AppConstants.Companion.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    val weatherService: WeatherService by lazy {
        retrofit.create(WeatherService::class.java)
    }

    val locationService: LocationService by lazy {
        retrofit.create(LocationService::class.java)

    }

    val hourlyService: HourlyService by lazy{
        retrofit.create(HourlyService::class.java)
    }

    val dailyService: DailyService by lazy{
        retrofit.create(DailyService::class.java)
    }
}