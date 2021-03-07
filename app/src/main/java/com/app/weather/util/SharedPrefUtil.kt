package com.app.weather.util

import android.app.Activity
import android.content.SharedPreferences


class SharedPrefUtil {
    companion object {


        fun saveLike(activity: Activity, id: String?) {
            val sharedPref: SharedPreferences = activity.getSharedPreferences("main", 0)
            val editor = sharedPref.edit()
            if (!sharedPref.getBoolean(id, false)) {
                editor.putBoolean(id, true)
            } else {
                editor.putBoolean(id, false)
            }
            editor.commit()
        }

        fun checkLike(activity: Activity, id: String):Boolean {
            val sharedPref: SharedPreferences = activity.getSharedPreferences("main", 0)
            return sharedPref.getBoolean(id, false);
        }
    }
}
