package com.example.three_modules.app.di.app

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.three_modules.app.data.CityRepository
import com.example.three_modules.app.data.CoinRepository
import com.example.three_modules.app.data.MainRepository
import com.example.three_modules.app.data.WeatherRepository
import com.example.three_modules.app.presentation.ui.fragments.coin.database.CoinDatabase
import com.example.three_modules.app.presentation.ui.fragments.coin.database.CoinsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun getCoinDao(coinDatabase: CoinDatabase): CoinsDao {
        return coinDatabase.coinsDao()
    }

    @Singleton
    @Provides
    fun getCoinDatabase(
        context: Context
    ): CoinDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CoinDatabase::class.java,
            "coin.db"
        ).build()
    }

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
    fun provideMainRepository(
        context: Context
    ): MainRepository = MainRepository(context = context)

    @Singleton
    @Provides
    fun provideRepository(
        context: Context
    ): CityRepository = CityRepository(context = context)

    @Singleton
    @Provides
    fun provideCoinRepository(
        context: Context,
        coinDatabase: CoinDatabase
    ): CoinRepository = CoinRepository(context = context, coinDatabase = coinDatabase)

    @Singleton
    @Provides
    fun provideWeatherRepository(
        context: Context
    ): WeatherRepository = WeatherRepository(context = context)
}