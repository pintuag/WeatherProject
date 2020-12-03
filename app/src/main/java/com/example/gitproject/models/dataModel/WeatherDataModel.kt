package com.example.gitproject.models.dataModel

data class WeatherDataModel(
    val `data`: ArrayList<WeatherData>
)

data class WeatherData(
    val rain: Int,
    val temp: Int,
    val time: Long,
    val wind: Int
)