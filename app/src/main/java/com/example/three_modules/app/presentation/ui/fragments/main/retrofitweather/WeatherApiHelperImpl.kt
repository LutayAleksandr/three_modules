package com.example.three_modules.app.presentation.ui.fragments.main.retrofitweather

import kotlinx.coroutines.flow.flow


class WeatherApiHelperImpl(private val apiService: WeatherRetrofitService): WeatherApiHelper {
    override fun getWeatherRetrofit(lat: String, lon: String) = flow {
        emit(apiService.getWeatherRetrofit(lat = lat,
            lon = lon))
    }
}