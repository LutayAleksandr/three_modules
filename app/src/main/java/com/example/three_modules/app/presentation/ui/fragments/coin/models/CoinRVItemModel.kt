package com.example.three_modules.app.presentation.ui.fragments.coin.models

import com.example.three_modules.R

data class CoinRVItemModel(
    val id: String,
    val name: String,
    val imageUrl: String,
    val currentPrice: Float,
    val priceChange24h: Float,
    var isSelected: Boolean = false,
    val color: Int,
 )

fun CoinJsonURLModel.toRVItemModel(index: Int): CoinRVItemModel {
    return CoinRVItemModel(
        id = this.id,
        name = this.name ,
        imageUrl = this.imageUrl,
        currentPrice = this.currentPrice,
        priceChange24h = this.priceChange24h,
        color = if (index % 2 == 0) R.color.blue else R.color.lightBlue,

    )
}

fun CoinEntity.toRVItemModel(index: Int): CoinRVItemModel {
    return CoinRVItemModel(
        id = this.id,
        name = this.name ,
        imageUrl = this.imageUrl,
        currentPrice = this.currentPrice,
        priceChange24h = this.priceChange24h,
        color = if (index % 2 == 0) R.color.blue else R.color.lightBlue,
        isSelected = this.isSelected
        )
}