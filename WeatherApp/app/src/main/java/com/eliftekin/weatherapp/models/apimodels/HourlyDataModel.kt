package com.eliftekin.weatherapp.models.apimodels

data class HourlyDataModel(
    val list: List<WeatherList>
)

data class WeatherList(
    val dt: Long,
    val main: HourlyMain,
    val weather: List<HourlyWeather>,
    val pop: Double
)

data class HourlyMain(
    val temp: Double
)

data class HourlyWeather(
    val id: Int
)
