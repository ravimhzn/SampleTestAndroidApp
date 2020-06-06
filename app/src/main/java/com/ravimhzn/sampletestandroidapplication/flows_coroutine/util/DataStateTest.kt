package com.ravimhzn.sampletestandroidapplication.flows_coroutine.util

import com.ravimhzn.sampletestandroidapplication.utils.Constants.Companion.UNKNOWN_ERROR


data class DataStateTest<T>(
    var error: ErrorState? = null,
    var data: T? = null,
    var stateEvent: StateEvent
) {

    companion object {

        fun <T> error(
            errorMessage: String?,
            stateEvent: StateEvent
        ): DataStateTest<T> {
            return DataStateTest(
                error = ErrorState(errorMessage?: UNKNOWN_ERROR),
                data = null,
                stateEvent = stateEvent
            )
        }

        fun <T> data(
            data: T? = null,
            stateEvent: StateEvent
        ): DataStateTest<T> {
            return DataStateTest(
                error = null,
                data = data,
                stateEvent = stateEvent
            )
        }
    }
}























