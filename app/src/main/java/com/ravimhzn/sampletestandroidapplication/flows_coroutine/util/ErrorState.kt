package com.ravimhzn.sampletestandroidapplication.flows_coroutine.util

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ErrorState(
    var message: String
) : Parcelable