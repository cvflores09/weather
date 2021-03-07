package com.app.weather.data.api

import com.app.weather.model.Weather
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("group")
    fun getWeatherList(@Query("id") id:String,@Query("appid") appid:String): Call<JsonObject>

    @GET("weather")
    fun getWeather(@Query("id") id:String,@Query("appid") appid:String): Call<Weather>

}