package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.feature.weather_screen.data.model.WeatherRemoteModel
import com.example.weatherfetcher.feature.weather_screen.ui.model.WeatherModel

fun WeatherRemoteModel.onDomain() = WeatherModel(
    temperature = this.main.temperature,
    windDeg = this.wind.deg
)