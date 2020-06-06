package com.ravimhzn.sampletestandroidapplication.flows_coroutine.util

import android.util.Log
import com.ravimhzn.sampletestandroidapplication.utils.Constants.Companion.DEBUG
import com.ravimhzn.sampletestandroidapplication.utils.Constants.Companion.TAG

fun printLogD(className: String?, message: String ) {
    if (DEBUG) {
        Log.d(TAG, "$className: $message")
    }
}