package com.example.three_modules.app.di.fragment

import com.example.three_modules.app.presentation.ui.fragments.main.MainFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule (private val mainFragment: MainFragment){

    @FragmentScope
    @Provides
    fun provideFragment(): MainFragment {
        return mainFragment
    }
}