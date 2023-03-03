package com.example.three_modules.app.di.app

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.three_modules.app.di.viewmodel.ViewModelModule
import com.example.three_modules.app.presentation.ui.fragments.coin.viewmodel.CoinViewModel
import com.example.three_modules.app.presentation.ui.toolbarlistener.ToolbarListenerManager
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelModule::class,
    ]
)
interface AppComponent {

    fun provideViewModelFactory(): ViewModelProvider.Factory
    fun provideContext(): Context
    fun provideToolbarListenerManager(): ToolbarListenerManager

    fun inject(coinViewModel: CoinViewModel)

}