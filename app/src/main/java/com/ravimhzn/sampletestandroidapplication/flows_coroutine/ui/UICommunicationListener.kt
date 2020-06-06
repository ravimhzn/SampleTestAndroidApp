package com.ravimhzn.sampletestandroidapplication.flows_coroutine.ui


interface UICommunicationListener {

    fun displayMainProgressBar(isLoading: Boolean)

    fun hideToolbar()

    fun showToolbar()

    fun hideStatusBar()

    fun showStatusBar()

    fun expandAppBar()

}