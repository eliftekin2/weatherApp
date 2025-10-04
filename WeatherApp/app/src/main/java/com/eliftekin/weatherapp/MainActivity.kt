package com.eliftekin.weatherapp

import android.Manifest
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.eliftekin.weatherapp.adapters.ViewPagerAdapter
import com.eliftekin.weatherapp.databinding.ActivityMainBinding
import com.eliftekin.weatherapp.fragments.HomeFragment
import com.eliftekin.weatherapp.fragments.WeeklyForecastFragment
import com.eliftekin.weatherapp.viewmodels.MainViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.Calendar
import kotlin.getValue

class MainActivity : AppCompatActivity() {

    //binding
    private lateinit var binding: ActivityMainBinding

    //lokasyon
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    //viewmodel
    lateinit var viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        //günün saatine göre temayı değiştir
        setCurrentTheme()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)

        setViewPager()
        checkPermission()
    }
    private fun checkPermission(){
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if(location != null){
                    viewmodel.getWeather(location.latitude, location.longitude)
                    viewmodel.getLocation(location.latitude, location.longitude)
                    viewmodel.getHourlyWeather(location.latitude, location.longitude)
                    viewmodel.getDailyWeather(location.latitude, location.longitude)
                }
            }
        }
        else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
        }

    }

    private fun setViewPager() {
        val fragments = listOf(HomeFragment(), WeeklyForecastFragment())
        val adapter = ViewPagerAdapter(this, fragments)

        binding.viewpager.adapter = adapter

    }

    private fun setCurrentTheme() {
        val currentTime = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        when (currentTime) {
            in 6..11 -> setTheme(R.style.MorningTheme)
            in 12..17 -> setTheme(R.style.DayTheme)
            else -> setTheme(R.style.NightTheme)
        }
    }

}