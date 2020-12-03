package com.example.gitproject.models.httpService

import com.example.gitproject.models.dataModel.WeatherDataModel
import com.example.gitproject.util.ApiConstants
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {

    @GET(ApiConstants.WEATHER_FORCAST_API)
    fun getWeatherForcastApi(): Observable<WeatherDataModel>

}