package com.example.three_modules.app.presentation.ui.fragments.coin.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coins_table")
data class CoinEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val imageUrl: String,
    val currentPrice: Float,
    val priceChange24h: Float,
    @ColumnInfo(name = "isSelected")
    var isSelected: Boolean = false,
    val marketCapRank: Int,
    @ColumnInfo(name = "selected_position")
    var selectedPosition: Int? = null
)

fun CoinJsonURLModel.toCoinEntity(): CoinEntity {
    return CoinEntity(
        id = this.id,
        name = this.name ,
        imageUrl = this.imageUrl,
        currentPrice = this.currentPrice,
        priceChange24h = this.priceChange24h,
        marketCapRank = this.marketCapRank
        )
}

fun CoinRVItemModel.toCoinEntityFromItem(index:Int): CoinEntity {
    return CoinEntity(
        id = this.id,
        name = this.name ,
        imageUrl = this.imageUrl,
        currentPrice = this.currentPrice,
        priceChange24h = this.priceChange24h,
        isSelected = this.isSelected,
        marketCapRank = this.marketCapRank,
    )
}