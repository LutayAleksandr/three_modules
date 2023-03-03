package com.example.three_modules.app.di.fragment

import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule (private val fragment: Fragment){

    @FragmentScope
    @Provides
    fun provideFragment(): Fragment {
        return fragment
    }
}