package com.example.three_modules.app.presentation.ui.toolbarlistener

import com.example.three_modules.app.data.MainRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToolbarListenerManager @Inject constructor(
    private val mainRepository: MainRepository,
) {

    private val listeners = mutableSetOf<ToolbarListener>()

    fun changeToolbarButtonState(isSaveButton: Boolean) {
        listeners.forEach {
            it.changeToolbarButtonState(isSaveButton = isSaveButton)
        }
    }

    fun addListener(listener: ToolbarListener) {
        listeners.add(element = listener)
    }

    fun removeListener(listener: ToolbarListener) {
        listeners.remove(element = listener)
    }

    suspend fun saveModules() {
        mainRepository.saveList()
    }


}