package com.app.weather.model

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class Weather(

    @SerializedName("name")
    var cityName : String,
    @SerializedName("weather")
    var currentWeather : JsonArray,
    @SerializedName("main")
    var temperature : JsonObject,
    @SerializedName("id")
    var id:String,
    var isLiked: Boolean


)