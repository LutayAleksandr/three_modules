package com.example.three_modules.app.di.activity

import android.app.Activity
import com.example.three_modules.app.di.app.AppComponent
import com.example.three_modules.app.presentation.activity.MainActivity
import com.example.three_modules.app.presentation.ui.toolbarlistener.ToolbarListenerManager
import dagger.Component

@ActivityScope
@Component(
    modules = [ActivityModule::class],
    dependencies = [AppComponent::class]
)
interface ActivityComponent {

    fun provideActivity(): Activity
    fun provideToolbarListenerManager(): ToolbarListenerManager
    fun inject(activity: MainActivity)

}