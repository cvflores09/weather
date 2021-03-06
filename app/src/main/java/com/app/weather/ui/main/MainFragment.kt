package com.app.weather.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.weather.R
import com.app.weather.adapter.WeatherAdapter
import com.app.weather.factory.MainViewModelFactory
import com.app.weather.model.Weather
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel

class MainFragment : Fragment() {

   // private var viewManager = LinearLayoutManager(activity)


    private lateinit var weatherRecyclerView : RecyclerView

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        var view = inflater.inflate(R.layout.main_fragment, container, false)
        weatherRecyclerView = view.findViewById(R.id.weatherRecyclerView)
        initialiseAdapter()
        return  view
    }
    private fun initialiseAdapter(){
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        if(weatherRecyclerView.layoutManager ==null)
        weatherRecyclerView.layoutManager = LinearLayoutManager(activity)

        observeData()
    }
    fun observeData(){


        val weatherList = ArrayList<Weather>()
        val weather = Weather("Manila","30","Cloud",false)

        val weather2 = Weather("Manila","30","Cloud",false)
        val weather3 = Weather("Manila","30","Cloud",false)
        weatherList.add(weather)
        weatherList.add(weather2)
        weatherList.add(weather3)

        weatherRecyclerView.adapter= WeatherAdapter(viewModel, weatherList)

        viewModel.weatherList.observe(this.viewLifecycleOwner, Observer {
            val newList = ArrayList<Weather>()
            val weather = Weather("Manila","30","Cloud",false)
            newList.add(weather)
            weatherRecyclerView.adapter= WeatherAdapter(viewModel, weatherList)
        })

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


    }


}