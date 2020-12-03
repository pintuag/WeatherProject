package com.example.gitproject.view.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.LocationManager
import android.view.View
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.gitproject.R
import com.example.gitproject.models.dataModel.WeatherData
import com.example.gitproject.models.dataModel.WeatherDataModel
import com.example.gitproject.models.httpService.Result
import com.example.gitproject.util.Constants
import com.example.gitproject.util.toast
import com.example.gitproject.view.adapters.WeatherListAdapter
import com.example.gitproject.view.baseFragment.BaseFragment
import com.example.gitproject.viewModels.WeatherListViewModel
import kotlinx.android.synthetic.main.no_record_found.*
import kotlinx.android.synthetic.main.progress_bar.*
import kotlinx.android.synthetic.main.weather_fragment.*


class WeatherFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    lateinit var weatherListViewModel: WeatherListViewModel

    lateinit var locationManager: LocationManager

    val weatherListAdapter = WeatherListAdapter()

    override fun getLayoutId() = R.layout.weather_fragment

    override fun init() {

        setLocationListeners()
        setRecyclerView()
        setSwipeRefreshLayout()
        initViewModels()
        callGitHubListApi()
    }

    @SuppressLint("MissingPermission")
    private fun setLocationListeners() {

        locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location = locationManager.getLastKnownLocation(
            locationManager.getBestProvider(
                Criteria(),
                false
            )
        )
        if (location != null) {
            val locationText = "Lat: ${Constants.formatDoubleNumber(location.latitude)} " +
                    "Long: ${Constants.formatDoubleNumber(location.longitude)}"
            weatherLocationLatLong.text = locationText
        }
    }


    private fun setRecyclerView() {

        weatherListRecyclerView.adapter = weatherListAdapter

    }

    private fun setSwipeRefreshLayout() {

        swipeRefresh.setOnRefreshListener(this)

    }

    override fun onRefresh() {
        callGitHubListApi()
        if (swipeRefresh.isRefreshing) {
            swipeRefresh.isRefreshing = false
        }
    }

    private fun initViewModels() {

        weatherListViewModel = getViewModelInstance(WeatherListViewModel::class.java)
        weatherListViewModel.weatherListLiveData.observe(this,
            Observer<Result<WeatherDataModel>> {
                when (it) {
                    is Result.Loading -> {
                        showProgressBar()
                    }

                    is Result.Success -> {
                        hideProgressBar()
                        if (!it.data.data.isNullOrEmpty()) {
                            setAdapterValues(it.data.data)
                            viewVisibility(true)
                        } else {
                            viewVisibility(false)
                        }
                    }
                    is Result.Error -> {
                        hideProgressBar()
                        activity!!.toast(it.exception.message.toString())
                    }
                }
            })

    }

    private fun setAdapterValues(data: List<WeatherData>) {
        weatherListAdapter.setWeatherListData(activity!!, data)
    }


    private fun callGitHubListApi() {

        weatherListViewModel.weatherListApiCall()
    }


    fun showProgressBar() {
        circleProgressBar.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        circleProgressBar.visibility = View.GONE
    }

    fun viewVisibility(visible: Boolean) {
        if (visible) {
            weatherListRecyclerView.visibility = View.VISIBLE
            noRecordsFound.visibility = View.GONE
        } else {
            weatherListRecyclerView.visibility = View.GONE
            noRecordsFound.visibility = View.VISIBLE
        }
    }
}