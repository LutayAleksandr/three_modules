package com.example.three_modules.app.di.fragment


import com.example.three_modules.app.di.activity.ActivityComponent
import com.example.three_modules.app.presentation.ui.fragments.main.MainFragment
import com.example.three_modules.app.presentation.ui.fragments.settings.SettingFragment
import com.example.three_modules.app.presentation.ui.toolbarlistener.ToolbarListenerManager
import dagger.Component


@FragmentScope
@Component(
    modules = [FragmentModule::class],
    dependencies = [ActivityComponent::class]
)
interface FragmentComponent {

    fun provideToolbarListenerManager(): ToolbarListenerManager
    fun inject(fragment: MainFragment)
    fun inject(fragment: SettingFragment)

}