package com.eliftekin.weatherapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eliftekin.weatherapp.databinding.WeeklyForecastCardBinding
import com.eliftekin.weatherapp.models.DailyWeather

class DailyRvAdapter(val list: List<DailyWeather>) : RecyclerView.Adapter<DailyRvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = WeeklyForecastCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = list[position]

        holder.binding.dayOfWeek.text= item.day
        holder.binding.weekWeatherIcon.setImageResource(item.icon)
        holder.binding.weekWeather.text = item.main
        holder.binding.weekWeatherTemp.text = item.temp
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(val binding: WeeklyForecastCardBinding): RecyclerView.ViewHolder(binding.root)
}