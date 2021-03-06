package com.app.weather.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.weather.R
import com.app.weather.model.Weather
import com.app.weather.ui.main.MainViewModel


class WeatherAdapter  (val viewModel:MainViewModel,var weatherList:ArrayList<Weather>):RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
            var root = LayoutInflater.from(parent.context).inflate(R.layout.weather_item,parent,false)
        return WeatherViewHolder(root)
    }

    override fun onBindViewHolder(holder: WeatherAdapter.WeatherViewHolder, position: Int) {
                holder.bind(weatherList.get(position))
    }

    override fun getItemCount(): Int {

        return weatherList.size
    }

     class WeatherViewHolder(private var binding: View):RecyclerView.ViewHolder(binding){


        fun bind(weather:Weather){

            binding.findViewById<TextView>(R.id.temperature).text = weather.currentWeather
            binding.findViewById<TextView>(R.id.city).text = weather.cityName
            binding.findViewById<ImageView>(R.id.like).visibility = if (weather.isLiked) View.VISIBLE else View.GONE
            binding.findViewById<TextView>(R.id.status).text = weather.status

        }
    }

}