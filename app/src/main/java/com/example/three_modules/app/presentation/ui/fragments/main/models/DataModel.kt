package com.example.three_modules.app.presentation.ui.fragments.main.models

sealed class DataModel {
    data class HeaderRVItemModel(
        val title: String): DataModel()

    data class MainRVItemModel(
        val buttonText: String,
        val itemType: MainItemType
    ): DataModel()
}

enum class MainItemType {
    CITY,
    WEATHER,
    COIN
}


