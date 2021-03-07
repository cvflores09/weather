package com.app.weather.util

import androidx.core.graphics.toColorInt
import java.math.BigDecimal
import java.math.RoundingMode

class TemperatureUtil {


    companion object {
        fun convertKelvinToCentigrade(kelvin: String): String {
            val centigrade = kelvin.toBigDecimal().subtract(BigDecimal(273))
            return centigrade.setScale(1, RoundingMode.UP).toDouble().toString()
        }
        fun convertKelvinToCentigradeNoDecimal(kelvin: String): String {
            val centigrade = kelvin.toBigDecimal().subtract(BigDecimal(273)).toBigInteger()
            return centigrade.toString()
        }

        fun temperatureColor(temp:String):Int{
            var temperature = BigDecimal(convertKelvinToCentigrade(temp))

            var hex = String()
            if(temperature<BigDecimal.ZERO)
                hex = "#1976D2"
            else if(temperature>=BigDecimal(0)&&temperature <BigDecimal(15))
                hex = "#26C6DA"
            else if(temperature>=BigDecimal(15)&&temperature <BigDecimal(30))
                hex = "#66BB6A"
            else if(temperature>=BigDecimal(30))
                hex = "#FF7043"




            return  hex.toColorInt()
        }
    }
}