package com.example.three_modules.app.di.activity

import android.app.Activity
import com.example.three_modules.app.presentation.ui.activity.MainActivity
import com.example.three_modules.app.di.app.AppComponent
import com.example.three_modules.test.TestManager
import com.example.three_modules.test.TestUtils
import dagger.Component

@ActivityScope
@Component(
    modules = [ActivityModule::class],
    dependencies = [AppComponent::class]
)
interface ActivityComponent {

    fun provideActivity(): Activity
    fun provideTestUtils(): TestUtils
    fun provideTestManager(): TestManager

    fun inject(activity: MainActivity)

}