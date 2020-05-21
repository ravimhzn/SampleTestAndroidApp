package com.ravimhzn.sampletestandroidapplication.di


import com.ravimhzn.sampletestandroidapplication.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    //    @MainScope
//    @ContributesAndroidInjector(
//        modules = [
//            MainModule::class,
//            MainFragmentBuildersModule::class,
//            MainViewModelModule::class
//        ]
//    )
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}
