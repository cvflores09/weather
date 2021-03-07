package com.app.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.weather.adapter.WeatherAdapter
import com.app.weather.data.api.RetrofitBuiler
import com.app.weather.model.Weather
import com.app.weather.ui.main.DetailFragment
import com.app.weather.ui.main.MainFragment
import com.app.weather.ui.main.MainViewModel
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        var detailFragment  = DetailFragment.newInstance()
        val bundle = Bundle()
        bundle.putString("id",view.tag.toString())
        detailFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .addToBackStack(mainFragment.javaClass.simpleName)
            .replace(R.id.container, detailFragment)
            .commit()
    }



    override fun onBackPressed() {


        if(supportFragmentManager.backStackEntryCount>0){
            supportFragmentManager.popBackStack()
        }else
        super.onBackPressed()
    }

}