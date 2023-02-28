package com.example.three_modules.app.presentation.ui.fragments.weather.datamodel

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.three_modules.app.presentation.ui.fragments.weather.models.WeatherEntity

@Dao
 abstract class WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(weather: MutableList<WeatherEntity>)

    @Query("UPDATE weather_table SET isSelected = :isSelected WHERE id =:id")
    abstract suspend fun updateSelection(isSelected: Boolean, id: Int)

    @Query("SELECT * FROM weather_table")
    abstract suspend fun getAllWeather(): MutableList<WeatherEntity>
}