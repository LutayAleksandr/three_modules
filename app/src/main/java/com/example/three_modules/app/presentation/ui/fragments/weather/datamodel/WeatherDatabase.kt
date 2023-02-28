package com.example.three_modules.app.presentation.ui.fragments.weather.datamodel

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherEntity

@Database(
    entities = [WeatherEntity::class], version = 1
)
abstract class WeatherDatabase: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}