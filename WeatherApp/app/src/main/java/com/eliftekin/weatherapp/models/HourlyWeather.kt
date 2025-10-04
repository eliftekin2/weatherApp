package com.eliftekin.weatherapp.models

data class HourlyWeather(
    val hour: String,
    val icon: Int,
    val temp: String,
    val pop: Double
)
