package com.example.three_modules.app

import android.app.Application
import com.example.three_modules.app.di.app.AppComponent
import com.example.three_modules.app.di.app.AppModule
import com.example.three_modules.app.di.app.DaggerAppComponent
import com.yandex.mapkit.MapKitFactory

class App: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(application = this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        MapKitFactory.setApiKey("3eb038a7-9dc2-4c92-95dc-7fc8bd7239de")
    }


    fun getCoinAppComponent(): AppComponent {
        return appComponent
    }
}