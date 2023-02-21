package com.example.three_modules.app

import android.app.Application
import com.example.three_modules.app.di.app.AppComponent
import com.example.three_modules.app.di.app.AppModule
import com.example.three_modules.app.di.app.DaggerAppComponent
import com.example.three_modules.app.presentation.ui.fragments.coin.database.AppDataBase
import com.example.three_modules.app.presentation.ui.fragments.coin.database.CoinRepositoryImpl
import com.example.three_modules.app.presentation.ui.fragments.coin.database.CoinRepositoryRoom

class App: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(application = this))
            .build()
    }

    private lateinit var dataBase: AppDataBase
    lateinit var repositoryRoom: CoinRepositoryRoom

    override fun onCreate() {
        super.onCreate()

        dataBase = AppDataBase.getDB(applicationContext)
        repositoryRoom = CoinRepositoryImpl(dataBase.coinsDao())
    }



}