package com.example.three_modules.app.presentation.ui.fragments.coin.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CoinItems")
data class EntityCoin(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,
    @ColumnInfo(name = "current_price")
    val current_price: Float,
    @ColumnInfo(name = "price_change_24h")
    val price_change_24h: Float,
    @ColumnInfo(name = "isSelected")
    var isSelected: Boolean = false,
)
