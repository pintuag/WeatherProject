package com.example.gitproject.models.repository

import android.annotation.SuppressLint
import com.example.gitproject.models.dataModel.WeatherDataModel
import com.example.gitproject.models.httpService.ResponseHandler
import com.example.gitproject.models.httpService.Result
import com.example.gitproject.models.httpService.ServiceCreator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException

object DataSource : DataSourceCalls {


    /*
    * this method is for calling the network api and set data in live data observer
    * */
    @SuppressLint("CheckResult")
    override fun weatherDataList(
        responseHandler: ResponseHandler<Result<WeatherDataModel>>
    ) {
        responseHandler.response(Result.Loading)
        val service = ServiceCreator.createService()
        val call = service.getWeatherForcastApi()
        call.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    responseHandler.response(Result.Success(it))
                },
                {
                    responseHandler.response(Result.Error(Exception(exceptionErrors(it))))
                }
            )
    }


    /*
    * Different type of exception handling
    * */
    fun exceptionErrors(throwable: Throwable): String {
        if (throwable is IOException) {
            // A network or conversion error happened
            return "Network Error"
        }
        if (throwable is IllegalStateException) {
            // data parsing error
            return "Data Parsing Error"
        }
        // any other network call exception
        return "Please try again Later"
    }

}