package com.app.weather.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.weather.data.api.RetrofitBuiler
import com.app.weather.model.Weather
import com.google.gson.Gson
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {


    var weatherList = MutableLiveData<ArrayList<Weather>>()
    var weather = MutableLiveData<Weather>()

    var errorMessage = MutableLiveData<String>()
            fun update(weathers : ArrayList<Weather>){

                weatherList.value = weathers
            }
            fun update(newWeather:Weather){
                weather.value  = newWeather
            }
            fun updateError(error:String){
                errorMessage.value = error
            }

    fun loadData(ids: ArrayList<String> ){
        val callBack = object : Callback<JsonObject> {


            override fun onFailure(call: Call<JsonObject>?, t: Throwable?) {
                Log.e("MainActivity", "Problem calling API {${t?.message}}")
                errorMessage.value =  t?.message
            }

            override fun onResponse(call: Call<JsonObject>?, response: Response<JsonObject>?) {
                response?.isSuccessful.let {
                    if (response!!.isSuccessful) {
                        //weatherList.value?.add(response.body()!!)
                        val list = response.body()?.getAsJsonArray("list")
                        val newWeatherList = ArrayList<Weather>()
                        for(jsonObject in list!!){
                           newWeatherList.add(Gson().fromJson(jsonObject,Weather::class.java) )
                        }
                        update(newWeatherList)
                    }


                }
            }
        }

        weatherList.value = ArrayList()
        var idsString = String()
        for(id in ids)
        {
                idsString = idsString+id+","
        }
        RetrofitBuiler.apiService.getWeatherList(idsString,"d2347535d52b2e3919965d45901a6693").enqueue(callBack)

    }
    fun loadData(id:String ){
        val callBack = object : Callback<Weather> {


            override fun onFailure(call: Call<Weather>?, t: Throwable?) {
                Log.e("MainActivity React Post", "Problem calling API {${t?.message}}")
                errorMessage.value =  t?.message
            }

            override fun onResponse(call: Call<Weather>?, response: Response<Weather>?) {
                response?.isSuccessful.let {
                    if (response!!.isSuccessful) {
                       update(response.body()!!)
                    }


                }
            }
        }




        RetrofitBuiler.apiService.getWeather(id,"d2347535d52b2e3919965d45901a6693").enqueue(callBack)

    }

}