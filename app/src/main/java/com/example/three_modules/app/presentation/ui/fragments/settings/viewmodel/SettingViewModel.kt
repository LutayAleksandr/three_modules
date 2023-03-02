package com.example.three_modules.app.presentation.ui.fragments.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.three_modules.app.data.MainRepository
import com.example.three_modules.app.presentation.ui.fragments.settings.database.SettingsDao
import com.example.three_modules.app.presentation.ui.fragments.settings.model.SettingRVItemModel
import com.example.three_modules.app.presentation.ui.fragments.settings.model.toItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {

    @Inject
    lateinit var settingsDao: SettingsDao

    private val _modules = MutableSharedFlow<List<SettingRVItemModel>>()
    val modules = _modules.asSharedFlow()
    private val _list = MutableSharedFlow<List<SettingRVItemModel>>()
    val list = _list.asSharedFlow()

    private var modulesList = listOf<SettingRVItemModel>()

    fun getAllModules() {
        viewModelScope.launch {
            mainRepository.loadModules()
            val settingListEntity = mainRepository.getAllModules()
            modulesList = settingListEntity.mapIndexed {index, settingEntity ->  settingEntity.toItem(index = index) }
            _modules.emit(value = modulesList)
        }
    }

//    suspend fun saveChanges() {
//
//    }

    fun buildList() {
        viewModelScope.launch {
            val settingListEntity = mainRepository.getAllModules()
            modulesList = settingListEntity.mapIndexed {index, settingEntity ->  settingEntity.toItem(index = index) }
            val recyclerViewList = modulesList
            _list.emit(recyclerViewList)
        }
    }
}