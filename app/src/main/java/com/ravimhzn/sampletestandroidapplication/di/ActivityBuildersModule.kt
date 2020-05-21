package com.ravimhzn.sampletestandroidapplication.di


import com.ravimhzn.sampletestandroidapplication.ui.MainActivity
import com.ravimhzn.sampletestandroidapplication.ui.di.MainFragmentBuildersModule
import com.ravimhzn.sampletestandroidapplication.ui.di.MainModule
import com.ravimhzn.sampletestandroidapplication.ui.di.MainScope
import com.ravimhzn.sampletestandroidapplication.ui.di.MainViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(
        modules = [
            MainModule::class,
            MainFragmentBuildersModule::class,
            MainViewModelModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}
