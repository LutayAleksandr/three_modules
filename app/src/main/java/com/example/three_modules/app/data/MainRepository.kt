package com.example.three_modules.app.data

import android.content.Context
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.three_modules.app.presentation.ui.fragments.settings.database.SettingsDatabase
import com.example.three_modules.app.presentation.ui.fragments.settings.model.SettingEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class MainRepository@Inject constructor(
    val context: Context,
    private val settingsDatabase: SettingsDatabase
    ) {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private  var modules: List<SettingEntity> = listOf()

    suspend fun loadModules() = withContext(scope.coroutineContext) {
        modules = settingsDatabase.settingsDao().getAllModules()
        if (modules.isEmpty()) {
            modules = listOf(
                SettingEntity(
                    id = 1,
                    textModules = "Погода",
                ),
                SettingEntity(
                    id = 2,
                    textModules = "Город",
                ),
                SettingEntity(
                    id = 3,
                    textModules = "Курс Криптовалют",
                )
            )
            settingsDatabase.settingsDao().insert(modules)
        }
    }

    suspend fun getAllModules(): List<SettingEntity> {
        return withContext(scope.coroutineContext) {
            return@withContext settingsDatabase.settingsDao().getAllModules()
        }
    }

    suspend fun updateList() = withContext(scope.coroutineContext) {
        settingsDatabase.settingsDao().insert(modules)
    }
}