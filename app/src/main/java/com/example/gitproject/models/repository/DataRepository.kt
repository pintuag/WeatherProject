package com.example.gitproject.models.repository

import com.example.gitproject.models.dataModel.WeatherDataModel
import com.example.gitproject.models.httpService.ResponseHandler
import com.example.gitproject.models.httpService.Result

class DataRepository(val dataSource: DataSource) {


    fun getweatherDataList(
        responseHandler: ResponseHandler<Result<WeatherDataModel>>
    ) {
        dataSource.weatherDataList(responseHandler)
    }

}