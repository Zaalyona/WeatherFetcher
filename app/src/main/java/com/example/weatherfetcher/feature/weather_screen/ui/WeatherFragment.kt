package com.example.weatherfetcher.feature.weather_screen.ui

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.weatherfetcher.R
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.math.roundToInt

class WeatherFragment : Fragment(R.layout.fragment_weather) {

    private val viewModel: WeatherScreenViewModel by sharedViewModel()

    private val tvTemperature: TextView by lazy { requireActivity().findViewById(R.id.tvTemperature) }
    private val tvWindDeg: TextView by lazy { requireActivity().findViewById(R.id.tvWindDeg) }

    private val collapsingToolbar: CollapsingToolbarLayout by lazy {
        requireActivity().findViewById(R.id.collapsingToolbar)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner, ::render)
    }

    private fun render(viewState: ViewState) {
        collapsingToolbar.title = "Погода в городе ${viewState.cityName}"

        //val winDeg = convertWinDegFromDouble(viewState.windDeg)

        tvTemperature.text = "${convertTemperatureFromDouble(viewState.temperature)} °C"
        tvWindDeg.text = "${convertWinDegFromDouble(viewState.windDeg)}"
        /*tvTemperature.text = convertTemperatureFromDouble(viewState.temperature)
        tvWindDeg.text = convertWinDegFromDouble(viewState.windDeg.toString())*/
    }

    private fun convertTemperatureFromDouble(stringTemperature: String): String {

        val string = stringTemperature
        val temperature = string.toDouble().roundToInt()

        return temperature.toString()
    }

    private fun convertWinDegFromDouble(stringWinDeg: String): String {

        val string = stringWinDeg

        val windString: String = when (string.toInt()) {
            0, 360 -> "Северный"
            90 -> "Восточный"
            180 -> "Южный"
            270 -> "Западный"
            in 1..89 -> "Северо-восточный"
            in 91..179 -> "Юго-восточный"
            in 181..269 -> "Юго-западный"
            in 271..359 -> "Северо-западный"
            else -> "Произошла ошибка"
        }

        return windString
    }
}