package com.example.three_modules.app.presentation.ui.fragments.coin.models

import com.google.gson.annotations.SerializedName

data class CoinJsonURLModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("current_price")
    val currentPrice: Float,
    @SerializedName("price_change_24h")
    val priceChange24h: Float,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int
)

//val mutableState = MutableStateFlow(RecyclerView.State())
