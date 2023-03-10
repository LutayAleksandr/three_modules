package com.example.three_modules.app.data

import android.content.Context
import com.example.three_modules.app.presentation.ui.fragments.settings.database.SettingsDatabase
import com.example.three_modules.app.presentation.ui.fragments.settings.model.SettingEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository@Inject constructor(
    val context: Context,
    private val settingsDatabase: SettingsDatabase
    ) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private  var modules: MutableList<SettingEntity> = mutableListOf()

    suspend fun loadModules() = withContext(scope.coroutineContext) {
        modules = settingsDatabase.settingsDao().getAllModules()
        if (modules.isEmpty()) {
            modules = mutableListOf(
                SettingEntity(
                    id = "City",
                    textModules = "Город",
                ),
                SettingEntity(
                    id = "Weather",
                    textModules = "Погода"
                    ,
                ),
                SettingEntity(
                    id = "Coin",
                    textModules = "Курс Криптовалют"
                )
            )
            settingsDatabase.settingsDao().insert(modules)
        }
    }

    suspend fun getAllModules(): MutableList<SettingEntity> {
        return withContext(scope.coroutineContext) {
            return@withContext settingsDatabase.settingsDao().getAllModules()
        }
    }

    fun updateList(list: MutableList<SettingEntity>) {
        modules = list
    }

    suspend fun saveList() = withContext(scope.coroutineContext) {
        settingsDatabase.settingsDao().insert(modules)
    }
}