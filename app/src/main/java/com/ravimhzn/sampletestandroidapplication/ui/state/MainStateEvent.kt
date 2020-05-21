package com.ravimhzn.sampletestandroidapplication.ui.state

sealed class MainStateEvent {

    class GetUserListEvent() : MainStateEvent()

    class None() : MainStateEvent()
}