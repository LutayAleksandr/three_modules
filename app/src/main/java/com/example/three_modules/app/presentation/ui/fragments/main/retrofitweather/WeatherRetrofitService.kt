package com.example.three_modules.app.presentation.ui.fragments.main.retrofitweather

import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherJsonApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRetrofitService {
    @GET("2.5/weather?")
    suspend fun getWeatherRetrofit(
        @Query("lat") lat: String = "",
        @Query("lon") lon: String = "",
        @Query("appid") apiKey: String = "744306690950ee0c0f3c9d4a8b31c716",
        @Query("lang") lang: String = "ru"
    ): WeatherJsonApiModel
}