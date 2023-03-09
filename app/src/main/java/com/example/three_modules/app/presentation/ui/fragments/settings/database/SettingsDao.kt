package com.example.three_modules.app.presentation.ui.fragments.settings.database

import androidx.room.*
import com.example.three_modules.app.presentation.ui.fragments.settings.model.SettingEntity

@Dao
abstract class SettingsDao {
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    abstract suspend fun insert(modules: MutableList<SettingEntity>)

    @Query("DELETE FROM modules_table")
    abstract suspend fun deleteAll()

    @Update
    abstract suspend fun update(modules: MutableList<SettingEntity>)

    @Query("SELECT * FROM modules_table")
    abstract suspend fun getAllModules(): MutableList<SettingEntity>

//    @Query ("SELECT * from modules_table ORDER BY position DESC" )
//    abstract suspend fun getAllPosition(): List<SettingEntity>
}
