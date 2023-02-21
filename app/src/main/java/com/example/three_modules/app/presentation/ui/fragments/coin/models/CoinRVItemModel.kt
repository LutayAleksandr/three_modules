package com.example.three_modules.app.presentation.ui.fragments.coin.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.three_modules.R

@Entity (tableName = "CoinItems")
data class CoinRVItemModel(
    @PrimaryKey
    val id: Int? = null,
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
    @ColumnInfo(name = "color")
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