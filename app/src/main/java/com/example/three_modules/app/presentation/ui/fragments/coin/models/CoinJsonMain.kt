package com.example.three_modules.app.presentation.ui.fragments.coin.models

import com.google.gson.annotations.SerializedName

data class CoinJsonMain(
    @SerializedName("current_price")
    val currentPrice: Float,
    @SerializedName("price_change_24h")
    val priceChange24h: Float,
)

