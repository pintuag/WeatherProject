package com.example.gitproject.view.baseActivity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.gitproject.R
import com.example.gitproject.util.Constants
import com.example.gitproject.view.fragments.WeatherFragment

/*
* Abstract class for using the common code and app permission
* */

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        init()
    }


    abstract fun getLayoutId(): Int
    abstract fun init()

    fun requestNeededPermissions() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                Constants.LOCATION_PERMISSION_REQUEST
            )
        } else {
            addFragment(WeatherFragment())
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            Constants.LOCATION_PERMISSION_REQUEST -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                        this,
                        "Please provide necessary permissions to use the app.",
                        Toast.LENGTH_SHORT
                    ).show()
                    requestNeededPermissions()
                } else {
                    addFragment(WeatherFragment())
                }
                return
            }
        }
    }

    /*
    * Extension function of adding a fragment
    * */
    public fun AppCompatActivity.addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(R.id.mainContainer, fragment, fragment::class.java.simpleName).commit()
    }

}