package com.ravimhzn.sampletestandroidapplication.ui

import android.util.Log
import com.ravimhzn.sampletestandroidapplication.ui.ResponseType.*
import com.ravimhzn.sampletestandroidapplication.utils.extension.displayErrorDialog
import com.ravimhzn.sampletestandroidapplication.utils.extension.displaySuccessDialog
import com.ravimhzn.sampletestandroidapplication.utils.extension.displayToast
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

abstract class BaseActivity : DaggerAppCompatActivity(), DataStateChangeListener {

    val TAG: String = "AppDebug ->"

    override fun onDataStateChange(dataState: DataState<*>?) {
        dataState?.let {
            GlobalScope.launch(Dispatchers.Main) {
                displayProgressBar(it.loading.isLoading)

                it.error?.let { errorEvent ->
                    handleStateError(errorEvent)
                }

                it.data?.let {
                    it.response?.let { eventResponse ->
                        handleStateResponse(eventResponse)
                    }
                }
            }
        }
    }

    private fun handleStateError(errorEvent: Event<StateError>) {
        errorEvent.getContentIfNotHandled()?.let { stateError ->

            when (stateError.response.responseType) {
                is Toast -> {
                    stateError.response.message?.let { message ->
                        displayToast(message)
                    }
                }
                is Dialog -> {
                    stateError.response.message?.let { message ->
                        displayErrorDialog(message = message)
                    }
                }
                is None -> {
                    Log.e(TAG, "handleStateError: ${stateError.response.message}");
                }
            }
        }
    }

    private fun handleStateResponse(event: Event<Response>) {
        event.getContentIfNotHandled()?.let { response ->

            when (response.responseType) {
                is Toast -> {
                    response.message?.let { message ->
                        displayToast(message)
                    }
                }
                is Dialog -> {
                    response.message?.let { message ->
                        displaySuccessDialog(message = message)
                    }
                }
                is None -> {
                    Log.e(TAG, "handleStateResponse: ${response.message}");
                }
            }
        }
    }

    abstract fun displayProgressBar(loading: Boolean)
}