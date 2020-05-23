package com.ravimhzn.sampletestandroidapplication.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.RequestManager
import com.ravimhzn.sampletestandroidapplication.di.ViewModelProviderFactory
import com.ravimhzn.sampletestandroidapplication.ui.DataStateChangeListener
import com.ravimhzn.sampletestandroidapplication.ui.viewModels.MainViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open class MainBaseFragment : DaggerFragment() {
    val TAG: String = "AppDebug"

    lateinit var stateChangeListener: DataStateChangeListener

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var requestManager: RequestManager //For Glide

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // setupActonBarWithNavController(activity as AppCompatActivity)
        viewModel = activity?.run {
            ViewModelProvider(this, providerFactory).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        cancelActiveJobs()
    }

    fun setupActonBarWithNavController(activity: AppCompatActivity) {
        /**
         * This will help add the title in the AppBar
         */
        NavigationUI.setupActionBarWithNavController(
            activity,
            findNavController()
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            stateChangeListener = context as DataStateChangeListener
        } catch (e: ClassCastException) {
            Log.e(TAG, "$context must implement DataStateChangeListener")
        }
    }

    fun cancelActiveJobs() {
        viewModel.cancelActiveJobs()
    }
}