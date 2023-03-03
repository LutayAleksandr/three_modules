package com.example.three_modules.app.presentation.ui.fragments.main.retrofitweather

import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherJsonApiModel
import kotlinx.coroutines.flow.Flow

interface WeatherApiHelper {

    fun getWeatherRetrofit(lat: String = "", lon: String = "",): Flow<WeatherJsonApiModel>
}