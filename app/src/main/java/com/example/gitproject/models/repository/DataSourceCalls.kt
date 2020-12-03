package com.example.gitproject.models.repository

import com.example.gitproject.models.dataModel.WeatherDataModel
import com.example.gitproject.models.httpService.ResponseHandler
import com.example.gitproject.models.httpService.Result

interface DataSourceCalls {

    fun weatherDataList(
        responseHandler:ResponseHandler<Result<WeatherDataModel>>
    )

}