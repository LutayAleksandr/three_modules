package com.example.three_modules.app.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.three_modules.app.presentation.ui.fragments.city.viewmodel.CityViewModel
import com.example.three_modules.app.presentation.ui.fragments.coin.viewmodel.CoinViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun provideViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CityViewModel::class)
    abstract fun cityViewModel(viewModel: CityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CoinViewModel::class)
    abstract fun coinViewModel(viewModel: CoinViewModel): ViewModel
}
