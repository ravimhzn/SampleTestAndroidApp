package com.ravimhzn.sampletestandroidapplication.flows_coroutine.di

import android.app.Application
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.di.DaggerAppComponent

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




