package com.example.gitproject.app

import android.app.Application
import com.example.gitproject.util.TypeFaceUtil

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        TypeFaceUtil.overrideFont(
            applicationContext,
            "SERIF",
            "fonts/roboto_regular.ttf"
        )
    }

}