package com.example.three_modules.app.di.activity

import android.app.Activity
import com.example.three_modules.app.presentation.activity.MainActivity
import com.example.three_modules.app.di.app.AppComponent
import dagger.Component

@ActivityScope
@Component(
    modules = [ActivityModule::class],
    dependencies = [AppComponent::class]
)
interface ActivityComponent {

    fun provideActivity(): Activity
    fun inject(activity: MainActivity)

}