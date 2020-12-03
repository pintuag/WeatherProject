package com.example.gitproject.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitproject.models.dataModel.WeatherDataModel
import com.example.gitproject.models.httpService.ResponseHandler
import com.example.gitproject.models.httpService.Result
import com.example.gitproject.models.repository.DataRepository

class WeatherListViewModel(application: Application, val dataRepository: DataRepository) :
    AndroidViewModel(application) {

    private val weatherListMutableLiveData = MutableLiveData<Result<WeatherDataModel>>()
    val weatherListLiveData: LiveData<Result<WeatherDataModel>>
        get() = weatherListMutableLiveData


    fun weatherListApiCall() {
        dataRepository.getweatherDataList(object :
            ResponseHandler<Result<WeatherDataModel>> {
            override fun response(response: Result<WeatherDataModel>) {
                weatherListMutableLiveData.value = response
            }
        })
    }

    override fun onCleared() {
        super.onCleared()

    }

}