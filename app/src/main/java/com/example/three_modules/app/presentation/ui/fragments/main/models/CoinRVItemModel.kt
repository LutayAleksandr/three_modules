package com.example.three_modules.app.presentation.ui.fragments.main.models

import com.google.gson.annotations.SerializedName

data class CoinRVItemModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("current_price")
    val current_price: Int,
    @SerializedName("price_change_24h")
    val price_change_24h: Int,
)
