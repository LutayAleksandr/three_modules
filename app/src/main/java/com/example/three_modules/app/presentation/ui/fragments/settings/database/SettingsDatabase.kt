package com.example.three_modules.app.presentation.ui.fragments.settings.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.three_modules.app.presentation.ui.fragments.settings.model.SettingEntity

@Database(
    entities = [SettingEntity::class], version = 1
)
abstract class SettingsDatabase: RoomDatabase() {

    abstract fun settingsDao(): SettingsDao
}