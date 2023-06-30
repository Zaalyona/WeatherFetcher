package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.feature.weather_screen.ui.model.WeatherModel

class WeatherRepoImpl(private val weatherRemoteSource: WeatherRemoteSource): WeatherRepo {

    override suspend fun getWeather(cityName: String): WeatherModel {
        return weatherRemoteSource.getWeather(cityName = cityName).onDomain()
    }
}