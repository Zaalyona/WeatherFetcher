package com.example.weatherfetcher.feature.weather_screen.data

import com.example.weatherfetcher.feature.weather_screen.data.model.WeatherRemoteModel
import retrofit2.Response

class WeatherRemoteSource(private val api: WeatherApi) {

    suspend fun getWeather(cityName: String): WeatherRemoteModel {
        return api.getWeather(query = cityName)
        //return api.getWeather(query = "Moscow")
    }
}