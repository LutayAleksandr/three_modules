package com.example.three_modules.app.presentation.ui.fragments.city.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.three_modules.app.presentation.ui.fragments.city.models.CityEntity

@Database(
    entities = [CityEntity::class], version = 1
)
abstract class CityDatabase: RoomDatabase() {

    abstract fun cityDao(): CityDao
}