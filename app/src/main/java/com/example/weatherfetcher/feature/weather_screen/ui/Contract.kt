package com.example.weatherfetcher.feature.weather_screen.ui

import com.example.weatherfetcher.base.Event
import com.example.weatherfetcher.feature.weather_screen.ui.model.WeatherModel

data class ViewState(
    val isLoading: Boolean,
    var cityName: String,
    val temperature: String,
    val windDeg: String
)

sealed class UiEvent() : Event {
    object OnButtonClicked : UiEvent()
    data class OnCityClicked(val cityName: String) : UiEvent()
}

sealed class DataEvent() : Event {
    data class OnWeatherFetchSucceed(val weather: WeatherModel): DataEvent()
    data class OnWeatherFetchFailed(val error: Throwable): DataEvent()
}