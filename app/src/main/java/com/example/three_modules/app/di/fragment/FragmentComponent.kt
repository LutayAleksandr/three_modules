package com.example.three_modules.app.di.fragment


import com.example.three_modules.app.di.activity.ActivityComponent
import dagger.Component


@FragmentScope
@Component(
    modules = [FragmentModule::class],
    dependencies = [ActivityComponent::class]
)
interface FragmentComponent {

}