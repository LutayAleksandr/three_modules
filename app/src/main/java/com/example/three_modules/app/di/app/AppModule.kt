package com.example.three_modules.app.di.app

import android.app.Application
import android.content.Context
import com.example.three_modules.test.TestManager
import com.example.three_modules.test.TestUtils
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
    fun provideTestUtils(
        context: Context
    ): TestUtils {
        return TestUtils(context)
    }

    @Singleton
    @Provides
    fun provideTestManager(
        context: Context,
        testUtils: TestUtils
    ): TestManager {
        return TestManager(
            context = context,
            testUtils = testUtils
        )
    }
}