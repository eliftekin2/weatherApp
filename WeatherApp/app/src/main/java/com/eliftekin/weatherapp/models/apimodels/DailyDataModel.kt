package com.eliftekin.weatherapp.models.apimodels

data class DailyDataModel(
    val list: List<DailyList>
)

data class DailyList(
    val dt: Long,
    val temp : Temp,
    val humidity: Int,
    val speed: Double,
    val pop: Double,
    val weather: List<DailyWeather>
)

data class Temp(
    val day: Double
)

data class DailyWeather(
    val id: Int,
    val main: String
)