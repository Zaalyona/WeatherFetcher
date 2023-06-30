package com.example.weatherfetcher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.example.weatherfetcher.feature.weather_screen.ui.MainScreenFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Android KTX Fragment
        supportFragmentManager.commit(allowStateLoss = false) {
            replace(R.id.container, MainScreenFragment())
        }
    }
}