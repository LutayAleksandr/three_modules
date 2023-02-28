package com.example.three_modules.app.presentation.ui.fragments.weather.states

import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherRVItemModel


sealed class WeatherStates {
    object Empty: WeatherStates()
    data class Success(val list: List<WeatherRVItemModel>): WeatherStates()
}

sealed class WeatherActions {
    object PopBackStack: WeatherActions()
}