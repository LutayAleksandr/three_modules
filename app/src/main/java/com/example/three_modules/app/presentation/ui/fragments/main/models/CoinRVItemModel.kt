package com.example.three_modules.app.presentation.ui.fragments.main.models

import androidx.recyclerview.widget.RecyclerView
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.MutableStateFlow

data class CoinRVItemModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("current_price")
    val current_price: Float,
    @SerializedName("price_change_24h")
    val price_change_24h: Float,
)

val mutableState = MutableStateFlow(RecyclerView.State())
