package com.example.three_modules.app.presentation.ui.fragments.main.models

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
    val countryName: String? = null
)