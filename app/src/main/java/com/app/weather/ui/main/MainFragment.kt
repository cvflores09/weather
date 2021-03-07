package com.app.weather.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

   // private var viewManager = LinearLayoutManager(activity)


    private lateinit var weatherRecyclerView : RecyclerView

    val ids  = ArrayList<String>()
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ids.add("1701668")
        ids.add("3067696")
        ids.add("1835848")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        var view = inflater.inflate(R.layout.main_fragment, container, false)
        weatherRecyclerView = view.findViewById(R.id.weatherRecyclerView)
        view.findViewById<SwipeRefreshLayout>(R.id.mainswipelayout).setOnRefreshListener {
            viewModel.loadData(ids)


        }
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
        viewModel.weatherList.observe(this.viewLifecycleOwner, Observer {
            weatherRecyclerView.adapter= WeatherAdapter(viewModel, viewModel.weatherList.value!!,this.requireActivity())
            mainswipelayout.isRefreshing = false
        })
        viewModel.errorMessage.observe(this.viewLifecycleOwner, Observer {
            Toast.makeText(this.context,viewModel.errorMessage.value,Toast.LENGTH_LONG).show()
            mainswipelayout.isRefreshing = false
        })
        viewModel.loadData(ids)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }


}