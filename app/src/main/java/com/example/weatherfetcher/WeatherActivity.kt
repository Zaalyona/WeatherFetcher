package com.example.weatherfetcher

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherfetcher.feature.weather_screen.WeatherInteractor
import com.example.weatherfetcher.feature.weather_screen.data.WeatherApiClient
import com.example.weatherfetcher.feature.weather_screen.data.WeatherRemoteSource
import com.example.weatherfetcher.feature.weather_screen.data.WeatherRepoImpl
import com.example.weatherfetcher.feature.weather_screen.ui.WeatherScreenPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID

class WeatherActivity : AppCompatActivity() {

    private lateinit var presenter: WeatherScreenPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        presenter = WeatherScreenPresenter(
            WeatherInteractor(
                WeatherRepoImpl(
                    WeatherRemoteSource(WeatherApiClient.getApi())
                )
            )
        )

        val tvTemperature = findViewById<TextView>(R.id.tvTemperature)
        val tvWindDeg = findViewById<TextView>(R.id.tvWindDirection)

        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                val temperatureString = presenter.getWeather()
                tvTemperature.text =((temperatureString.toDouble() - 273.5).toInt()).toString()

                val windDeg = presenter.getWind().toInt()
                var windString = ""

                when (windDeg) {
                    0, 360 -> windString = "Северный"
                    90 -> windString = "Восточный"
                    180 -> windString = "Южный"
                    270 -> windString = "Западный"
                    in 1..89 -> windString = "Северо-восточный"
                    in 91..179 -> windString = "Юго-восточный"
                    in 181..269 -> windString = "Юго-западный"
                    in 271..359 -> windString = "Северо-западный"
                }

                tvWindDeg.text = windString
            }

        }
    }
}