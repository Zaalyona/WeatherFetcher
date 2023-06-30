package com.example.weatherfetcher.feature.weather_screen.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.weatherfetcher.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.Date

class MainScreenFragment : Fragment(R.layout.fragment_main_screen) {

    private val viewModel: WeatherScreenViewModel by sharedViewModel()
    private val citiesArray = arrayOf(
        "Москва",
        "Санкт-Петербург",
        "Екатеринбург",
        "Сочи")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sdf = SimpleDateFormat("dd.MM.yyyy")
        val currentDate = sdf.format(Date())

        val tvCurrentDate = requireActivity().findViewById<TextView>(R.id.tvCurrentDate)

        tvCurrentDate.text = currentDate

        val spinner = requireActivity().findViewById<Spinner>(R.id.spnWeather)

        val arrayAdapter = ArrayAdapter<String>(
            requireActivity().applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            citiesArray)
        spinner.adapter = arrayAdapter
        spinner.setSelection(0)

        val btnWeather = requireActivity().findViewById<Button>(R.id.btnWeather)

        btnWeather.setOnClickListener {

            viewModel.processUiEvent(UiEvent.OnCityClicked(spinner.selectedItem.toString()))
            viewModel.processUiEvent(UiEvent.OnButtonClicked)

            //Android KTX Fragment
            requireActivity().supportFragmentManager.commit(allowStateLoss = false) {
                replace(R.id.container, WeatherFragment())
            }
        }
    }
}