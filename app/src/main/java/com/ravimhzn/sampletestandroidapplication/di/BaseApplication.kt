package com.ravimhzn.sampletestandroidapplication.di

import android.app.Application
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import com.ravimhzn.sampletestandroidapplication.di.DaggerAppComponent

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
open class BaseApplication: Application() {

    private val TAG: String = "AppDebug"

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

   open fun initAppComponent() {
       appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
    }

}




