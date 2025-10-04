package com.eliftekin.weatherapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eliftekin.weatherapp.databinding.HourlyForecastCardBinding
import com.eliftekin.weatherapp.models.HourlyWeather

class HourlyRvAdapter(val list: List<HourlyWeather>): RecyclerView.Adapter<HourlyRvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = HourlyForecastCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = list[position]
        holder.binding.hour.text = item.hour
        holder.binding.hourlyWeatherIcon.setImageResource(item.icon)
        holder.binding.hourlyTemp.text = item.temp
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(val binding: HourlyForecastCardBinding): RecyclerView.ViewHolder(binding.root)
}