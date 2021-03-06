package com.app.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.weather.adapter.WeatherAdapter
import com.app.weather.model.Weather
import com.app.weather.ui.main.DetailFragment
import com.app.weather.ui.main.MainFragment
import com.app.weather.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {



    val mainFragment = MainFragment.newInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, mainFragment)
                    .commitNow()
        }
    }

    fun selectItem(view:View){

        supportFragmentManager.beginTransaction()
            .addToBackStack(mainFragment.javaClass.simpleName)
            .replace(R.id.container, DetailFragment.newInstance())

            .commit()
    }

    override fun onBackPressed() {


        if(supportFragmentManager.backStackEntryCount>0){
            supportFragmentManager.popBackStack()
        }else
        super.onBackPressed()
    }

}