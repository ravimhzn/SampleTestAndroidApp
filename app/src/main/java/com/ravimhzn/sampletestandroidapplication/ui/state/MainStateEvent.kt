package com.ravimhzn.sampletestandroidapplication.ui.state

sealed class MainStateEvent {

    class GetUserListEvent() : MainStateEvent()

    data class GetPhotoAlbumListEvent(val id: Int) : MainStateEvent()

    class None() : MainStateEvent()
}