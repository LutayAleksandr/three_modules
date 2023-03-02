package com.example.three_modules.app.di.app

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.three_modules.app.data.CityRepository
import com.example.three_modules.app.data.CoinRepository
import com.example.three_modules.app.data.MainRepository
import com.example.three_modules.app.data.WeatherRepository
import com.example.three_modules.app.presentation.ui.fragments.city.database.CityDao
import com.example.three_modules.app.presentation.ui.fragments.city.database.CityDatabase
import com.example.three_modules.app.presentation.ui.fragments.coin.database.CoinDatabase
import com.example.three_modules.app.presentation.ui.fragments.coin.database.CoinsDao
import com.example.three_modules.app.presentation.ui.fragments.settings.database.SettingsDao
import com.example.three_modules.app.presentation.ui.fragments.settings.database.SettingsDatabase
import com.example.three_modules.app.presentation.ui.fragments.weather.datamodel.WeatherDao
import com.example.three_modules.app.presentation.ui.fragments.weather.datamodel.WeatherDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun getSettingDao(settingsDatabase: SettingsDatabase): SettingsDao {
        return settingsDatabase.settingsDao()
    }

    @Singleton
    @Provides
    fun getSettingDatabase(
        context: Context
    ): SettingsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            SettingsDatabase::class.java,
            "modules.db"
        ).build()
    }

    @Singleton
    @Provides
    fun getWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao {
        return weatherDatabase.weatherDao()
    }

    @Singleton
    @Provides
    fun getWeatherDatabase(
        context: Context
    ): WeatherDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            WeatherDatabase::class.java,
            "weather.db"
        ).build()
    }

    @Singleton
    @Provides
    fun getCityDao(cityDatabase: CityDatabase): CityDao {
        return cityDatabase.cityDao()
    }

    @Singleton
    @Provides
    fun getCityDatabase(
        context: Context
    ): CityDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            CityDatabase::class.java,
            "city.db"
        ).build()
    }

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
        context: Context,
        settingsDatabase: SettingsDatabase
    ): MainRepository = MainRepository(context = context, settingsDatabase = settingsDatabase)

    @Singleton
    @Provides
    fun provideRepository(
        context: Context,
        cityDatabase: CityDatabase
    ): CityRepository = CityRepository(context = context, cityDatabase = cityDatabase)

    @Singleton
    @Provides
    fun provideCoinRepository(
        context: Context,
        coinDatabase: CoinDatabase
    ): CoinRepository = CoinRepository(context = context, coinDatabase = coinDatabase)

    @Singleton
    @Provides
    fun provideWeatherRepository(
        context: Context,
        weatherDatabase: WeatherDatabase
    ): WeatherRepository = WeatherRepository(context = context, weatherDatabase = weatherDatabase)
}