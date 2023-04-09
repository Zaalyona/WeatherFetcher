package com.example.weatherfetcher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class MainActivity : AppCompatActivity() {

    val citiesArray = arrayOf("Moscow", "Saint Petersburg", "Ekaterinburg", "Sochi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val currentDate = sdf.format(Date())

        val textCurrentDate = findViewById<TextView>(R.id.idCurrentDate)

        textCurrentDate.text = currentDate

        val spinner = findViewById<Spinner>(R.id.spnWeather)
        val arrayAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            citiesArray)
        spinner.adapter = arrayAdapter

        val btnWeather = findViewById<Button>(R.id.btnWeather)
        btnWeather.setOnClickListener {
            Intent(this, WeatherActivity::class.java).also(::startActivity)
        }


    }
}