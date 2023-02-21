package com.example.three_modules.app.presentation.ui.fragments.main.models

import com.example.three_modules.app.presentation.ui.fragments.coin.models.CoinRVItemModel

sealed class DataModel {
    data class HeaderRVItemModel(
        val title: String): DataModel()

    data class MainRVItemModel(
        val buttonText: String,
        val itemType: MainItemType
    ): DataModel()

    data class MainCoinRVItemModel(
        val buttonText: String,
        val itemType: MainItemType,
        val coins: List<CoinRVItemModel>
    ): DataModel()
}

enum class MainItemType {
    CITY,
    WEATHER,
    COIN
}


