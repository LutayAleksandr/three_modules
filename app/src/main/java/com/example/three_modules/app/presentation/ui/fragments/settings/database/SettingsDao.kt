package com.example.three_modules.app.presentation.ui.fragments.settings.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.three_modules.app.presentation.ui.fragments.settings.model.SettingEntity

@Dao
abstract class SettingsDao {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    abstract suspend fun insert(modules: List<SettingEntity>)

    @Query("SELECT * FROM modules_table")
    abstract suspend fun getAllModules(): List<SettingEntity>

//    @Query ("SELECT * from modules_table ORDER BY position DESC" )
//    abstract suspend fun getAllPosition(): List<SettingEntity>
}
