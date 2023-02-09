package com.example.three_modules.app.presentation.ui.fragments.coin.models

import com.example.three_modules.R

data class CoinRVItemModel(
     val name: String,
     val imageUrl: String,
     val current_price: Float,
     val price_change_24h: Float,
     val color: Int,
     var isSelected: Boolean = false
 )

fun CoinJsonURLModel.toRVItemModel(index: Int): CoinRVItemModel {
    return CoinRVItemModel(
        name = this.name ?: "",
        imageUrl = this.imageUrl ?: "",
        current_price = this.current_price,
        price_change_24h = this.price_change_24h,
        color = if (index % 2 == 0) R.color.blue else R.color.lightBlue,
    )
}