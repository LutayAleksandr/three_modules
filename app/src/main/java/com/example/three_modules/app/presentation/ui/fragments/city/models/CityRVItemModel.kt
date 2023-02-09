package com.example.three_modules.app.presentation.ui.fragments.city.models

import com.example.three_modules.R


data class CityRVItemModel(
    val id: Int,
    val color: Int,
    val cityName: String,
    val countryName: String,
    var isSelected: Boolean = false
)

fun CityJsonModel.toRVItemModel(index: Int): CityRVItemModel {
    return CityRVItemModel(
        id = this.id ?: 0,
        color = if (index % 2 == 0) R.color.blue else R.color.lightBlue,
        cityName = this.cityName ?: "",
        countryName = this.countryName ?: ""
    )
}