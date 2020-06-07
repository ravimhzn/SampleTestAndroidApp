package com.ravimhzn.sampletestandroidapplication.utils

import com.ravimhzn.sampletestandroidapplication.utils.Constants.Companion.UNKNOWN_ERROR


data class DataState<T>(
    var error: ErrorState? = null,
    var data: T? = null,
    var stateEvent: StateEvent
) {

    companion object {

        fun <T> error(
            errorMessage: String?,
            stateEvent: StateEvent
        ): DataState<T> {
            return DataState(
                error = ErrorState(
                    errorMessage ?: UNKNOWN_ERROR
                ),
                data = null,
                stateEvent = stateEvent
            )
        }

        fun <T> data(
            data: T? = null,
            stateEvent: StateEvent
        ): DataState<T> {
            return DataState(
                error = null,
                data = data,
                stateEvent = stateEvent
            )
        }
    }
}























