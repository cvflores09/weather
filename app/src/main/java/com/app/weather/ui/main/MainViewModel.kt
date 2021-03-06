package com.app.weather.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.weather.model.Weather

class MainViewModel : ViewModel() {


    var weatherList = MutableLiveData<ArrayList<Weather>>()

            fun update(weathers : ArrayList<Weather>){

                weatherList.value = weathers
            }

}