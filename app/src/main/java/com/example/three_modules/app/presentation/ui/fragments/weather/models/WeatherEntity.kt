package com.example.three_modules.app.presentation.ui.fragments.weather.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "weather_table")
data class WeatherEntity(
    @PrimaryKey
    val id: Int? = null,
    val cityName: String? = null,
    val countryName: String? = null,
    val latitude: Double,
    val longitude: Double,
    @ColumnInfo(name = "isSelected")
    var isSelected: Boolean = false,
)

fun WeatherJsonModel.toWeatherEntity(): WeatherEntity {
    return WeatherEntity(
        id = this.id,
        cityName = this.cityName,
        countryName = this.countryName,
        latitude = this.latitude,
        longitude = this.longitude
    )
}

fun WeatherRVItemModel.toWeatherEntityFromItem(): WeatherEntity {
    return WeatherEntity(
        id = this.id,
        cityName = this.cityName,
        countryName = this.countryName,
        isSelected = this.isSelected,
        latitude = this.latitude,
        longitude = this.longitude
    )
}
