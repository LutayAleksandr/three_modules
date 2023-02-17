package com.example.three_modules.app.presentation.ui.fragments.coin.models

import com.example.three_modules.R

data class CoinRVItemModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val current_price: Float,
    val price_change_24h: Float,
    var isSelected: Boolean = false,
    val color: Int,
 )

fun CoinJsonURLModel.toRVItemModel(index: Int): CoinRVItemModel {
    return CoinRVItemModel(
        id = index + 1,
        name = this.name ?: "",
        imageUrl = this.imageUrl ?: "",
        current_price = this.current_price,
        price_change_24h = this.price_change_24h,
        color = if (index % 2 == 0) R.color.blue else R.color.lightBlue,

    )
}