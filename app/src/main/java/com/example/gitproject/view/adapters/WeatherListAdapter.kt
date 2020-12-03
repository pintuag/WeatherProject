package com.example.gitproject.view.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitproject.R
import com.example.gitproject.models.dataModel.WeatherData
import com.example.gitproject.util.Constants
import kotlinx.android.synthetic.main.weather_recylerview_item.view.*

class WeatherListAdapter : RecyclerView.Adapter<WeatherListAdapter.RecyclerViewHolder>() {

    lateinit var context: Context
    var weatherList = emptyList<WeatherData>()

    fun setWeatherListData(
        context: Context,
        weatherList: List<WeatherData>
    ) {
        this.context = context
        this.weatherList = weatherList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.weather_recylerview_item, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        val weatherListModel = weatherList[position]
        holder.apply {
            temptext.text = "${weatherListModel.temp}° C"
            timeText.text = Constants.convertTimeStampToDate(weatherListModel.time)
            rainText.text = "${weatherListModel.rain}%"
            windText.text = "${weatherListModel.wind} km/h"
        }

    }


    inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val temptext = view.temptext
        val timeText = view.timeText
        val rainText = view.rainText
        val windText = view.windText

    }

}