package com.ravimhzn.sampletestandroidapplication.fragments

import androidx.fragment.app.FragmentFactory
import androidx.lifecycle.ViewModelProvider
import com.codingwithmitch.espressodaggerexamples.util.GlideManager
import com.ravimhzn.sampletestandroidapplication.ui.fragments.FragmentPictureDetails
import com.ravimhzn.sampletestandroidapplication.ui.fragments.FragmentPictureList
import com.ravimhzn.sampletestandroidapplication.ui.fragments.FragmentUserList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Singleton
class MainFragmentFactory
@Inject
constructor(
    private val viewModelFactory: ViewModelProvider.Factory,
    private val requestManager: GlideManager
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String) =

        when (className) {

            FragmentUserList::class.java.name -> {
                val fragment = FragmentUserList(viewModelFactory)
                fragment
            }

            FragmentPictureList::class.java.name -> {
                val fragment = FragmentPictureList(viewModelFactory)
                fragment
            }

            FragmentPictureDetails::class.java.name -> {
                val fragment = FragmentPictureDetails(viewModelFactory, requestManager)
                fragment
            }

            else -> {
                super.instantiate(classLoader, className)
            }
        }
}












