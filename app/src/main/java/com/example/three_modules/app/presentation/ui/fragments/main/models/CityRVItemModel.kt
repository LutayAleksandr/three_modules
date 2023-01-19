package com.example.three_modules.app.presentation.ui.fragments.main.models

data class CityRVItemModel (
    val cityName: String,
    val countryName: String,
    val color: Int,
    val itemType: CityItemType
    )
enum class CityItemType{
    ADD
}