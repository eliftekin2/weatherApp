package com.eliftekin.weatherapp.models

data class CurrentWeather(
    val id : Int,
    val temp: Double,
    val main: String,
    val humidity: Int,
    val windSpeed: Double
)
