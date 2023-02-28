package com.example.three_modules.app.presentation.ui.fragments.main.retrofitweather

object WeatherCommon {
    private const val baseURL = "https://api.openweathermap.org/data/"

    val retrofitService: WeatherRetrofitService
        get() = WeatherRetrofit.getRetrofitClient(baseURL)
            .create(WeatherRetrofitService::class.java)
}