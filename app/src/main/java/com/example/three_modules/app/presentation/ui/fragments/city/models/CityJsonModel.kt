package com.example.three_modules.app.presentation.ui.fragments.city.models

import com.google.gson.annotations.SerializedName


data class CitiesJsonModel(
    val list: List<CityJsonModel>
)

data class CityJsonModel(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("cityName")
    val cityName: String? = null,
    @SerializedName("countryName")
    val countryName: String? = null,
    @SerializedName("latitude")
    val latitude: Double,
    @SerializedName("longitude")
    val longitude: Double,
)