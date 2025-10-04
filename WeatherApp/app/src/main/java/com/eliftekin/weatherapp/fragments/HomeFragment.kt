package com.eliftekin.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eliftekin.weatherapp.adapters.HourlyRvAdapter
import com.eliftekin.weatherapp.databinding.FragmentHomeBinding
import com.eliftekin.weatherapp.viewmodels.MainViewModel

class HomeFragment : Fragment() {

    //binding
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    //viewmodel
    private lateinit var viewmodel: MainViewModel

    //recyclerview
    private lateinit var hourlyRvAdapter: HourlyRvAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        getDate() //gün bilgisi
        getLocation() //lokasyon bilgisi
        getWeather() //hava durumu bilgisi
        getHourly()
        getError()
    }

    private fun getError() {
        viewmodel.error.observe(viewLifecycleOwner){error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getHourly() {
        viewmodel.hourlyWeather.observe(viewLifecycleOwner) { list ->
            hourlyRvAdapter = HourlyRvAdapter(list)

            binding.hourlyRecyclerview.layoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.hourlyRecyclerview.adapter = hourlyRvAdapter

            //yağış olasılığı
            binding.rainPosibility.text = list[0].pop.toString()
        }
    }

    private fun getLocation() {
        viewmodel.location.observe(viewLifecycleOwner) { location ->
            binding.city.text = location
        }
    }

    private fun getWeather() {
        viewmodel.currentWeather.observe(viewLifecycleOwner) { weatherData ->
            binding.weatherIcon.setImageResource(weatherData.id)
            binding.weatherTemp.text = weatherData.temp.toString()
            binding.weatherDesc.text = weatherData.main
            binding.humidity.text = weatherData.humidity.toString()
            binding.wind.text = weatherData.windSpeed.toString()
        }
    }

    private fun getDate() {
        viewmodel.currentDay.observe(viewLifecycleOwner) {
            binding.date.text = it
        }
    }

}