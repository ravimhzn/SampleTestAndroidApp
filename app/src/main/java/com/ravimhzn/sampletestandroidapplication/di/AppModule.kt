package com.ravimhzn.sampletestandroidapplication.di

import android.app.Application
import com.bumptech.glide.Glide
import com.codingwithmitch.espressodaggerexamples.util.GlideManager
import com.codingwithmitch.espressodaggerexamples.util.GlideRequestManager
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Singleton

/* Alternative for Test: 'TestAppModule' */
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Module
object AppModule{

    @JvmStatic
    @Singleton
    @Provides
    fun provideGlideRequestManager(
        application: Application
    ): GlideManager {
        return GlideRequestManager(
            Glide.with(application)
        )
    }

}








