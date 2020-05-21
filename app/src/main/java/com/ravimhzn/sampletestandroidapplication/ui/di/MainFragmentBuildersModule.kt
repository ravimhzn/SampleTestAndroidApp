package com.ravimhzn.sampletestandroidapplication.ui.di

import com.ravimhzn.sampletestandroidapplication.ui.fragments.FragmentPictureDetails
import com.ravimhzn.sampletestandroidapplication.ui.fragments.FragmentPictureList
import com.ravimhzn.sampletestandroidapplication.ui.fragments.FragmentUserList
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeFragmentPictureDetails(): FragmentPictureDetails

    @ContributesAndroidInjector()
    abstract fun contributeFragmentPictureList(): FragmentPictureList

    @ContributesAndroidInjector()
    abstract fun contributeChangeFragmentUserList(): FragmentUserList

}