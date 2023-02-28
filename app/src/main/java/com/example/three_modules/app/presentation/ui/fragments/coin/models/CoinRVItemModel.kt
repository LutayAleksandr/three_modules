package com.example.three_modules.app.presentation.ui.fragments.coin.models

data class CoinRVItemModel(
    val id: String,
    val name: String,
    val imageUrl: String,
    val currentPrice: Float,
    val priceChange24h: Float,
    var isSelected: Boolean = false,
    val marketCapRank: Int,
 )

fun CoinJsonURLModel.toRVItemModel(index: Int): CoinRVItemModel {
    return CoinRVItemModel(
        id = this.id,
        name = this.name ,
        imageUrl = this.imageUrl,
        currentPrice = this.currentPrice,
        priceChange24h = this.priceChange24h,
        marketCapRank = this.marketCapRank

    )
}

fun CoinEntity.toRVItemModel(index: Int): CoinRVItemModel {
    return CoinRVItemModel(
        id = this.id,
        name = this.name ,
        imageUrl = this.imageUrl,
        currentPrice = this.currentPrice,
        priceChange24h = this.priceChange24h,
        marketCapRank = this.marketCapRank,
        isSelected = this.isSelected
        )
}
