package com.ravimhzn.sampletestandroidapplication.flows_coroutine.di

import androidx.fragment.app.FragmentFactory
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.fragments.MainFragmentFactory
import com.codingwithmitch.espressodaggerexamples.util.GlideManager
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Module
object FragmentModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideMainFragmentFactory(
        viewModelFactory: MainViewModelFactory,
        glideManager: GlideManager
    ): FragmentFactory{
        return MainFragmentFactory(viewModelFactory, glideManager)
    }

}















