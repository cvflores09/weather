package com.app.weather.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.weather.R
import com.app.weather.model.Weather
import com.app.weather.ui.main.MainViewModel
import com.app.weather.util.SharedPrefUtil
import com.app.weather.util.TemperatureUtil


class WeatherAdapter  (val viewModel:MainViewModel,var weatherList:ArrayList<Weather>,val activity:Activity):RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
            var root = LayoutInflater.from(parent.context).inflate(R.layout.weather_item,parent,false)
        return WeatherViewHolder(root,activity)
    }

    override fun onBindViewHolder(holder: WeatherAdapter.WeatherViewHolder, position: Int) {
                holder.bind(weatherList.get(position))
    }

    override fun getItemCount(): Int {
        return weatherList.size

    }

     class WeatherViewHolder(private var binding: View,val activity: Activity):RecyclerView.ViewHolder(binding){


        fun bind(weather:Weather){


            binding.tag = weather.id
            binding.findViewById<TextView>(R.id.temperature).text = TemperatureUtil.convertKelvinToCentigrade(weather.temperature.get("temp").toString())+ " °C"
            binding.findViewById<TextView>(R.id.city).text = weather.cityName
            binding.findViewById<ImageView>(R.id.like).visibility = if (SharedPrefUtil.checkLike(activity,weather.id)) View.VISIBLE else View.GONE
            binding.findViewById<TextView>(R.id.status).text = weather.currentWeather.get(0).asJsonObject.get("main").toString()
            binding.setBackgroundColor(TemperatureUtil.temperatureColor(weather.temperature.get("temp").toString()))

        }
    }

}