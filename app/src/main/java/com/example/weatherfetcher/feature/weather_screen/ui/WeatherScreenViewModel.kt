package com.example.weatherfetcher.feature.weather_screen.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherfetcher.base.BaseViewModel
import com.example.weatherfetcher.base.Event
import com.example.weatherfetcher.feature.weather_screen.WeatherInteractor
import kotlinx.coroutines.launch

class WeatherScreenViewModel(val interactor: WeatherInteractor): BaseViewModel<ViewState>() {

    suspend fun getWeather(): String {
        return interactor.getWeather()
    }

    override fun initialViewState(): ViewState = ViewState(temperature = "")

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UiEvent.OnButtonClicked -> {
                var temperature = ""
                try {
                    temperature = getWeather()
                    //getWeather()
                } catch (e: java.lang.Exception) {
                    Log.e("ERROR", e.localizedMessage)
                }
                return previousState.copy(temperature = temperature)
            }
            else -> return null
        }
    }
}