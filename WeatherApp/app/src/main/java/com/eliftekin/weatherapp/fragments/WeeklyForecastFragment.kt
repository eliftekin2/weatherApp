package com.eliftekin.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.eliftekin.weatherapp.R
import com.eliftekin.weatherapp.adapters.DailyRvAdapter
import com.eliftekin.weatherapp.databinding.FragmentWeeklyForecastBinding
import com.eliftekin.weatherapp.viewmodels.MainViewModel

class WeeklyForecastFragment : Fragment() {

    private var _binding: FragmentWeeklyForecastBinding? = null
    private val binding get()= _binding!!

    //viewmodel
    private lateinit var viewmodel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWeeklyForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        getDaily()
        getTomorrow()
    }

    private fun getTomorrow() {
        viewmodel.tomorrow.observe(viewLifecycleOwner){ weather ->
            binding.tomorrowWeatherIcon.setImageResource(weather.icon)
            binding.tomorrowTemp.text = weather.temp
            binding.tomorrowWeather.text = weather.main
            binding.tomorrowHumidity.text = weather.humidity
            binding.tomorrowWind.text = weather.windSpeed
            binding.tomorrowRainPosibility.text = weather.pop

        }
    }

    private fun getDaily() {
        binding.dailyRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewmodel.dailyWeather.observe(viewLifecycleOwner){list ->
            val adapter = DailyRvAdapter(list)
            binding.dailyRv.adapter = adapter
        }

    }

}