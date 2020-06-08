package com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state

import com.ravimhzn.sampletestandroidapplication.utils.StateEvent

sealed class MainStateEvent :
    StateEvent {

    class GetUserListEvent() : MainStateEvent() {
        override fun errorInfo(): String {
            return "Unable to retrieve all user lists"
        }

        override fun toString(): String {
            return "GetUserListEvent"
        }
    }

    class GetPictureList(val id: Int) : MainStateEvent() {
        override fun errorInfo(): String {
            return "Unable to retrieve picture lists"
        }

        override fun toString(): String {
            return "GetPictureList"
        }
    }
}