package com.example.three_modules.app.di.app

import android.content.Context
import com.example.three_modules.test.TestManager
import com.example.three_modules.test.TestUtils
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun provideContext(): Context
    fun provideTestUtils(): TestUtils
    fun provideTestManager(): TestManager
}