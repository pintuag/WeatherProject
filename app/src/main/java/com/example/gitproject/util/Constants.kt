package com.example.gitproject.util

import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

object Constants {


    const val LOCATION_PERMISSION_REQUEST = 101

    fun formatDoubleNumber(number: Double): String? {
        return DecimalFormat("##.####").format(number)
    }

    fun convertTimeStampToDate(timeStamp: Long): String {
        val format = SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH)
        return format.format(Date(timeStamp * 1000))
    }

}