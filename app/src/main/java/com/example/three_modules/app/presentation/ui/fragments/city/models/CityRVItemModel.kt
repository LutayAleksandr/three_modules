package com.example.three_modules.app.presentation.ui.fragments.city.models


data class CityRVItemModel(
    val id: Int,
    val cityName: String,
    val countryName: String,
    val latitude: Double,
    val longitude: Double,
    var isSelected: Boolean = false
)

fun CityJsonModel.toRVItemModel(index: Int): CityRVItemModel {
    return CityRVItemModel(
        id = this.id ?: 0,
        cityName = this.cityName ?: "",
        countryName = this.countryName ?: "",
        latitude = this.latitude,
        longitude = this.longitude
    )
}

fun CityEntity.toRVItemModelFromCityEntity(index: Int): CityRVItemModel {
    return CityRVItemModel(
        id = this.id ?: 0,
        cityName = this.cityName ?: "",
        countryName = this.countryName ?: "",
        latitude = this.latitude,
        longitude = this.longitude,
        isSelected = this.isSelected
    )
}