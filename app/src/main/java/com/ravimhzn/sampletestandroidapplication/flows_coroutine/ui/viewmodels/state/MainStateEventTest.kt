package com.ravimhzn.sampletestandroidapplication.flows_coroutine.ui.viewmodels.state

import com.ravimhzn.sampletestandroidapplication.flows_coroutine.util.StateEvent

sealed class MainStateEventTest : StateEvent {

    class GetUserListEvent() : MainStateEventTest() {
        override fun errorInfo(): String {
            return "Unable to retrieve all user lists"
        }

        override fun toString(): String {
            return "GetUserListEvent"
        }
    }
}