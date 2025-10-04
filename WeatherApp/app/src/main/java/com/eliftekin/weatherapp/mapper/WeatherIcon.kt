package com.eliftekin.weatherapp.mapper

import com.eliftekin.weatherapp.R

object WeatherIcon {
    fun getIconRes(id: Int):Int{
        return when(id){
            in 200..232 -> R.drawable.thunderstorm
            in 300..321 -> R.drawable.shower_rain
            in 500..531 -> R.drawable.rain
            in 600..622 -> R.drawable.snow
            in 701..781 -> R.drawable.mist
            in 801..804 -> R.drawable.few_clouds
            else -> R.drawable.clear_sky
        }

    }
}