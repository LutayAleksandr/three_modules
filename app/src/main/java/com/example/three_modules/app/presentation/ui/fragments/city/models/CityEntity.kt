package com.example.three_modules.app.presentation.ui.fragments.city.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.three_modules.app.presentation.ui.fragments.coin.models.toCoinEntity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "cities_table")
data class CityEntity(
    @PrimaryKey
    val id: Int? = null,
    val cityName: String? = null,
    val countryName: String? = null,
    @ColumnInfo(name = "isSelected")
    var isSelected: Boolean = false,
)

fun CityJsonModel.toCityEntity(): CityEntity {
    return CityEntity(
        id = this.id,
        cityName = this.cityName,
        countryName = this.countryName
    )
}

fun CityRVItemModel.toCityEntityFromItem(): CityEntity {
    return CityEntity(
        id = this.id,
        cityName = this.cityName,
        countryName = this.countryName,
        isSelected = this.isSelected
    )
}