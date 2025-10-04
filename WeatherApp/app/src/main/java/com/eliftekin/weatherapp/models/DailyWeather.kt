package com.eliftekin.weatherapp.models

data class DailyWeather(
    val day: String,
    val icon: Int,
    val temp: String,
    val humidity: String,
    val windSpeed: String,
    val pop: String,
    val main: String
)