package com.example.three_modules.app.presentation.ui.fragments.main.models

import kotlinx.serialization.Serializable

@Serializable
data class CityRVItemModel(
    val cityName: String,
    val countryName: String,
    val color: Int
)