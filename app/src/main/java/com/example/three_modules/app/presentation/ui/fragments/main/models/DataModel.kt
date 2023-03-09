package com.example.three_modules.app.presentation.ui.fragments.main.models

import com.example.three_modules.app.presentation.ui.fragments.city.models.CityEntity
import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherJsonApiModel

sealed class DataModel {
    data class HeaderRVItemModel(
        val title: String,
        val id: Int): DataModel()

    data class MainRVItemModel(
        val buttonText: String,
        val itemType: MainItemType,
        val coordinates: List<Coordinates>,
        val id: Int
    ): DataModel()

    data class MainCoinRVItemModel(
        val buttonText: String,
        val itemType: MainItemType,
        val coins: List<CoinRVItemModel>,
        val id: Int
    ): DataModel()

    data class MainWeatherItemModel(
        val buttonText: String,
        val itemType: MainItemType,
        val weather: WeatherJsonApiModel?,
        val id: Int
    ): DataModel()
}

data class Coordinates(
    var latitude: Double,
    var longitude: Double
)

enum class MainItemType {
    CITY,
    WEATHER,
    COIN
}

fun CityEntity.toCoordinates(): Coordinates {
    return Coordinates(
        latitude = this.latitude,
        longitude = this.longitude
    )
}


