package com.example.three_modules.app.presentation.ui.viewmodels.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class MainViewModel : ViewModel() {
    val massage: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
}