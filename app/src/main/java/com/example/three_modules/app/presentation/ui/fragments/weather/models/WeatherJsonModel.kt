package com.example.three_modules.app.presentation.ui.fragments.weather.models

import com.google.gson.annotations.SerializedName

data class WeatherJsonModel(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("cityName")
    val cityName: String? = null,
    @SerializedName("countryName")
    val countryName: String? = null,
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
)