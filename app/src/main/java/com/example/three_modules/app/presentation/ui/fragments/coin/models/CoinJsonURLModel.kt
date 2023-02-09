package com.example.three_modules.app.presentation.ui.fragments.coin.models

import com.google.gson.annotations.SerializedName

data class CoinJsonURLModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("current_price")
    val current_price: Float,
    @SerializedName("price_change_24h")
    val price_change_24h: Float,
)

//val mutableState = MutableStateFlow(RecyclerView.State())
