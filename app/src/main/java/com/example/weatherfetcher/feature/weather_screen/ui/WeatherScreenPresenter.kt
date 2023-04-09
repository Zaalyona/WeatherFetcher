package com.example.weatherfetcher.feature.weather_screen.ui

import com.example.weatherfetcher.feature.weather_screen.WeatherInteractor

class WeatherScreenPresenter(val interactor: WeatherInteractor) {

    suspend fun getWeather(): String {
        return interactor.getWeather()
    }

    suspend fun getWind(): String {
        return interactor.getWind()
    }
}