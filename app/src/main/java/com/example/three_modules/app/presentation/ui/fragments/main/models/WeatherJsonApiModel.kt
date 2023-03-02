package com.example.three_modules.app.presentation.ui.fragments.weather.models

import com.google.gson.annotations.SerializedName

data class WeatherJsonApiModel(
    @SerializedName("weather")
    val weather: List<Weather>,
    @SerializedName("main")
    val main: Main,
    @SerializedName("name")
    val cityName: String,
    @SerializedName("visibility")
    val visibility: Float,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("clouds")
    val clouds: Clouds,
)

data class Weather(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
)

data class Main(
    @SerializedName("temp")
    val temp: Float,
    @SerializedName("feels_like")
    val feelsLikeTemp: Float,
    @SerializedName("pressure")
    val pressure: Float,
    @SerializedName("humidity")
    val humidity: Float,
)

data class Wind(
    @SerializedName("speed")
    val speed: Float,
)

data class Clouds(
    @SerializedName("all")
    val clouds: Int
)
