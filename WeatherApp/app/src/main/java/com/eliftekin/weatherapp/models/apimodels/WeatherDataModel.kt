package com.eliftekin.weatherapp.models.apimodels

data class WeatherDataModel(
    val weather: List<Weather>,
    val main: Main,
    val wind: Wind
)

data class Weather(
    val id: Int,
    val main: String,
)

data class Main(
    val temp: Double,
    val humidity: Int,
)

data class Wind(
    val speed: Double
)
