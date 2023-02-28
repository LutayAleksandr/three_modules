package com.example.three_modules.app.presentation.ui.fragments.weather.models

data class WeatherRVItemModel(
    val id: Int,
    val cityName: String,
    val countryName: String,
    val latitude: Double,
    val longitude: Double,
    var isSelected: Boolean = false
)

fun WeatherJsonModel.toRVItemModel(index: Int): WeatherRVItemModel {
    return WeatherRVItemModel(
        id = this.id ?: 0,
        cityName = this.cityName ?: "",
        countryName = this.countryName ?: "",
        latitude = this.latitude,
        longitude = this.longitude
    )
}

fun WeatherEntity.toRVItemModelFromWeatherEntity(index: Int): WeatherRVItemModel {
    return WeatherRVItemModel(
        id = this.id ?: 0,
        cityName = this.cityName ?: "",
        countryName = this.countryName ?: "",
        latitude = this.latitude,
        longitude = this.longitude,
        isSelected = this.isSelected
    )
}