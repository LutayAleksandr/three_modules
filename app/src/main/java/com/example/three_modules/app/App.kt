package com.example.three_modules.app

import android.app.Application
import com.example.three_modules.app.di.app.AppComponent
import com.example.three_modules.app.di.app.AppModule
import com.example.three_modules.app.di.app.DaggerAppComponent

class App: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(application = this))
            .build()
    }


    fun getCoinAppComponent(): AppComponent {
        return appComponent
    }
}