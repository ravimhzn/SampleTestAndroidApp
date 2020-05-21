package com.ravimhzn.sampletestandroidapplication.ui

import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity(), DataStateChangeListener {

    val TAG: String = "AppDebug ->"


    override fun onDataStateChange(dataState: DataState<*>?) {
        TODO("Not yet implemented")
    }
}