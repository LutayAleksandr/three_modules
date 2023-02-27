package com.example.three_modules.app.presentation.ui.fragments.city.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities_table")
data class CityEntity(
    @PrimaryKey
    val id: Int? = null,
    val cityName: String? = null,
    val countryName: String? = null,
    val latitude: Double,
    val longitude: Double,
    @ColumnInfo(name = "isSelected")
    var isSelected: Boolean = false,
)

fun CityJsonModel.toCityEntity(): CityEntity {
    return CityEntity(
        id = this.id,
        cityName = this.cityName,
        countryName = this.countryName,
        latitude = this.latitude,
        longitude = this.longitude
    )
}

fun CityRVItemModel.toCityEntityFromItem(): CityEntity {
    return CityEntity(
        id = this.id,
        cityName = this.cityName,
        countryName = this.countryName,
        isSelected = this.isSelected,
        latitude = this.latitude,
        longitude = this.longitude
    )
}