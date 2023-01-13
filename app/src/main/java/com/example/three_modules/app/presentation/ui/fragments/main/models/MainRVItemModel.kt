package com.example.three_modules.app.presentation.ui.fragments.main.models

data class MainRVItemModel(
    val buttonText: String,
    val itemType: MainItemType
)

enum class MainItemType {
    CITY,
    WEATHER,
    COIN
}
