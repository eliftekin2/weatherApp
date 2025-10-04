package com.eliftekin.weatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eliftekin.weatherapp.mapper.WeatherIcon
import com.eliftekin.weatherapp.models.CurrentWeather
import com.eliftekin.weatherapp.models.HourlyWeather
import com.eliftekin.weatherapp.models.DailyWeather
import com.eliftekin.weatherapp.repository.WeatherRepo
import kotlinx.coroutines.launch
import java.util.Date
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainViewModel: ViewModel() {

    //gün bilgisi
    private val _currentDay = MutableLiveData<String>()
    val currentDay: LiveData<String> = _currentDay

    //hava durumu bilgisi
    private val _currentWeather = MutableLiveData<CurrentWeather>()
    val currentWeather: LiveData<CurrentWeather> = _currentWeather

    //error
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    //lokasyon adı
    private val _location = MutableLiveData<String>()
    val location: LiveData<String> = _location

    //saatlik have durumu bilgisi
    private val _hourlyWeather = MutableLiveData<List<HourlyWeather>>()
    val hourlyWeather = _hourlyWeather

    //günlük hava durumu bilgisi
    private  val _dailyWeather = MutableLiveData<List<DailyWeather>>()
    val dailyWeather = _dailyWeather

    //yarının hava durumu bilgisi
    private val _tomorrow = MutableLiveData<DailyWeather>()
    val tomorrow = _tomorrow

    private val weatherRepo = WeatherRepo()

    init {
        getCurrentDay()
    }

    private fun getCurrentDay() {
        val calendar = Calendar.getInstance()
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1

        when(month) {
            1 -> _currentDay.value = "Jan $day"
            2 -> _currentDay.value = "Feb $day"
            3 -> _currentDay.value = "Mar $day"
            4 -> _currentDay.value = "Apr $day"
            5 -> _currentDay.value = "May $day"
            6 -> _currentDay.value = "Jun $day"
            7 -> _currentDay.value = "Jul $day"
            8 -> _currentDay.value = "Aug $day"
            9 -> _currentDay.value = "Sep $day"
            10 -> _currentDay.value = "Oct $day"
            11 -> _currentDay.value = "Nov $day"
            12 -> _currentDay.value = "Dec $day"
        }

    }

    fun getWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            try {
                val response = weatherRepo.getWeather(lat, lon)

                val id = WeatherIcon.getIconRes(response.weather[0].id)
                val temp = response.main.temp
                val main = response.weather[0].main
                val humidity = response.main.humidity
                val windSpeed = response.wind.speed

                val currentWeather = CurrentWeather(id, temp, main, humidity, windSpeed)
                _currentWeather.value = currentWeather

            }
            catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun getHourlyWeather(lat: Double, lon: Double){
        viewModelScope.launch {
            try {
                val responseList = weatherRepo.getHourly(lat, lon).list
                val list = responseList.map {
                    val id = WeatherIcon.getIconRes(it.weather[0].id)
                    val temp = it.main.temp.toString()
                    val hour = convertDtToHour(it.dt)
                    val pop = it.pop
                    HourlyWeather(hour, id, temp, pop)
                }
                _hourlyWeather.value = list
            }
            catch (e: Exception){
                _error.value = e.message

            }
        }
    }

    private fun convertDtToDay(dt: Long): String {
        val date = Date(dt * 1000)
        val sdf = SimpleDateFormat("EEE", Locale.getDefault())
        return sdf.format(date)
    }

    fun getDailyWeather(lat: Double, lon: Double){
        viewModelScope.launch {
            try {
                val responseList = weatherRepo.getDaily(lat, lon).list
                val list = responseList.map {
                    val day = convertDtToDay(it.dt)
                    val id = WeatherIcon.getIconRes(it.weather[0].id)
                    val main = it.weather[0].main
                    val temp = it.temp.day.toInt().toString()
                    val humidity = it.humidity.toString()
                    val windSpeed = it.speed.toString()
                    val pop = it.pop.toString()

                    DailyWeather(day, id, temp, humidity, windSpeed, pop, main)
                }

                if (list.size > 1) {
                    _tomorrow.value = list[1]
                    _dailyWeather.value = list.drop(2)
                }
            }
            catch (e: Exception){
                _error.value = e.message
            }
        }
    }

    private fun convertDtToHour(dt: Long): String {
        val date = Date(dt * 1000)
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(date)
    }

    fun getLocation(lat: Double, lon: Double){
        viewModelScope.launch {
            try {
                val response = weatherRepo.getLocation(lat, lon)
                _location.value = response
            }
            catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
