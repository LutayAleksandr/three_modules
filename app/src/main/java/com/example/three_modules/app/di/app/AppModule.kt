package com.example.three_modules.app.di.app

import android.app.Application
import android.content.Context
import com.example.three_modules.app.data.CityRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun provideContext(): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return application
    }

    @Singleton
    @Provides
    fun provideRepository(
        context: Context
    ): CityRepository = CityRepository(context = context)
}