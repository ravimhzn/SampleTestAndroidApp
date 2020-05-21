package com.ravimhzn.sampletestandroidapplication.ui

/**
 * Will use this interface to check 3 different status of data
 * Loading
 * Error
 * Data
 */
interface DataStateChangeListener {
    fun onDataStateChange(dataState: DataState<*>?)
}