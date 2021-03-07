package com.app.weather.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.app.weather.R
import com.app.weather.util.SharedPrefUtil
import com.app.weather.util.TemperatureUtil
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.weather_item.*

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var cityId : String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.detail_fragment, container, false)

        val clickListener = View.OnClickListener {view ->
            SharedPrefUtil.saveLike(this.requireActivity(),this.arguments?.getString("id"))
            viewModel.weather.value?.isLiked = SharedPrefUtil.checkLike(this.requireActivity(),cityId)
            setLikeIcon()
        }
        view.findViewById<ImageView>(R.id.likeImageView).setOnClickListener (clickListener)
        view.findViewById<SwipeRefreshLayout>(R.id.swipelayout).setOnRefreshListener {
            viewModel.loadData(cityId)

        }
        initializeData()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)


    }

    fun initializeData(){


        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        cityId = this.requireArguments().getString("id","")
        viewModel.loadData(cityId)

        viewModel.weather.observe(this.viewLifecycleOwner, Observer {
            val weather = viewModel.weather.value
            cityName.text = weather?.cityName
            temperatureDetail.text = TemperatureUtil.convertKelvinToCentigrade(weather?.temperature?.get("temp").toString())+"° C"
            statusDetail.text = weather?.currentWeather?.get(0)?.asJsonObject?.get("main").toString()

            val high = "high "+ TemperatureUtil.convertKelvinToCentigradeNoDecimal(weather?.temperature?.get("temp_max").toString())+"° C"
            val low = "low " + TemperatureUtil.convertKelvinToCentigradeNoDecimal(weather?.temperature?.get("temp_min").toString())+"° C"

            highlow.text = high + "/" + low
            setLikeIcon()
            swipelayout.isRefreshing = false

        })
        viewModel.errorMessage.observe(this.viewLifecycleOwner, Observer {
            Toast.makeText(this.context,viewModel.errorMessage.value, Toast.LENGTH_LONG).show()
            swipelayout.isRefreshing = false
        })
    }

    fun setLikeIcon(){
        likeImageView.setImageResource (if (SharedPrefUtil.checkLike(this.requireActivity(),cityId)) R.drawable.like else R.drawable.likewhite)
    }

}