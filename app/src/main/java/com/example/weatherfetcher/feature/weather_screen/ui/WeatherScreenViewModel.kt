package com.example.weatherfetcher.feature.weather_screen.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.weatherfetcher.base.BaseViewModel
import com.example.weatherfetcher.base.Event
import com.example.weatherfetcher.feature.weather_screen.WeatherInteractor
import kotlinx.coroutines.launch

class WeatherScreenViewModel(val interactor: WeatherInteractor): BaseViewModel<ViewState>() {

    override fun initialViewState(): ViewState = ViewState(
        isLoading = false,
        cityName = "",
        temperature = "",
        windDeg = ""
    )

    override fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnCityClicked -> {
                return previousState.copy(cityName = event.cityName)
            }

            is UiEvent.OnButtonClicked -> {

                viewModelScope.launch {
                    interactor.getWeather(previousState.cityName).fold(
                        onError = {
                            processDataEvent(DataEvent.OnWeatherFetchFailed(error = it))
                            Log.d("Weather -> ", "Error")
                        },
                        onSuccess = {
                            processDataEvent(DataEvent.OnWeatherFetchSucceed(it))
                            Log.d("Weather -> ", "Success")
                        }
                    )
                }

                return previousState.copy(isLoading = true)
            }

            is DataEvent.OnWeatherFetchSucceed -> {
                return previousState.copy(
                    isLoading = false,
                    temperature = event.weather.temperature,
                    windDeg = event.weather.windDeg)
                    /*temperature = event.weather.temperature.temperature,
                    windDeg = event.weather.windDeg.windDeg)*/
            }

            is DataEvent.OnWeatherFetchFailed -> {
                Log.d("ViewModelError", "-> OnWeatherFetchFailed")
                return null
            }

            else -> return null
        }
    }
}