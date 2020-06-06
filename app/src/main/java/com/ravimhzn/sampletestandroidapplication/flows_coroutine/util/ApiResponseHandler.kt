package com.ravimhzn.sampletestandroidapplication.flows_coroutine.util

import com.ravimhzn.sampletestandroidapplication.utils.Constants.Companion.NETWORK_ERROR
import com.ravimhzn.sampletestandroidapplication.utils.Constants.Companion.UNKNOWN_ERROR

abstract class ApiResponseHandler<ViewState, Data>(
    response: ApiResult<Data?>,
    stateEvent: StateEvent
) {
    val getResult: DataStateTest<ViewState> = when (response) {

        is ApiResult.GenericError -> {
            DataStateTest.error(
                errorMessage = stateEvent.errorInfo()
                        + "\n\nReason: " + response.errorMessage,
                stateEvent = stateEvent
            )
        }

        is ApiResult.NetworkError -> {
            DataStateTest.error(
                errorMessage = stateEvent.errorInfo()
                        + "\n\nReason: " + NETWORK_ERROR,
                stateEvent = stateEvent
            )
        }

        is ApiResult.Success -> {
            if (response.value == null) {
                DataStateTest.error(
                    errorMessage = stateEvent.errorInfo()
                            + "\n\nReason: " + UNKNOWN_ERROR,
                    stateEvent = stateEvent
                )
            } else {
                handleSuccess(resultObj = response.value)
            }
        }

    }

    abstract fun handleSuccess(resultObj: Data): DataStateTest<ViewState>

}