package com.example.three_modules.app.presentation.ui.fragments.weather.models

import com.example.three_modules.R

data class WeatherRVItemModel(
    val id: Int,
    val color: Int,
    val cityName: String,
    val countryName: String,
    var isSelected: Boolean = false
)

fun WeatherJsonModel.toRVItemModel(index: Int): WeatherRVItemModel {
    return WeatherRVItemModel(
        id = this.id ?: 0,
        color = if (index % 2 == 0) R.color.blue else R.color.lightBlue,
        cityName = this.cityName ?: "",
        countryName = this.countryName ?: ""
    )
}