package com.ravimhzn.sampletestandroidapplication.ui.viewmodels

import android.os.Parcelable
import com.codingwithmitch.espressodaggerexamples.ui.viewmodel.getCurrentViewStateOrNew
import com.ravimhzn.sampletestandroidapplication.model.AlbumListResponse
import com.ravimhzn.sampletestandroidapplication.model.UserListResponse
import com.ravimhzn.sampletestandroidapplication.utils.ErrorStack
import com.ravimhzn.sampletestandroidapplication.utils.ErrorState
import com.ravimhzn.sampletestandroidapplication.utils.printLogD
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
fun MainViewModel.setUserList(userList: List<UserListResponse>) {
    val update = getCurrentViewStateOrNew()
    update.fragmentUserList.arrUserList = userList
    setViewState(update)
}

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
fun MainViewModel.setUserListResponse(userListResponse: UserListResponse) {
    val update = getCurrentViewStateOrNew()
    update.fragmentUserList.userListResponse = userListResponse
    setViewState(update)
}

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
fun MainViewModel.setPictureList(albumListResponse: List<AlbumListResponse>) {
    val update = getCurrentViewStateOrNew()
    update.fragmentPictureList.arrAlbumListResponse = albumListResponse
    setViewState(update)
}

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
fun MainViewModel.setAlbumListResponse(albumtListResponse: AlbumListResponse) {
    val update = getCurrentViewStateOrNew()
    update.fragmentPictureList.albumListResponse = albumtListResponse
    setViewState(update)
}

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
fun MainViewModel.clearActiveJobCounter() {
    val update = getCurrentViewStateOrNew()
    update.activeJobCounter.clear()
    setViewState(update)
}

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
fun MainViewModel.addJobToCounter(stateEventName: String) {
    val update = getCurrentViewStateOrNew()
    update.activeJobCounter.add(stateEventName)
    setViewState(update)
}

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
fun MainViewModel.removeJobFromCounter(stateEventName: String) {
    val update = getCurrentViewStateOrNew()
    update.activeJobCounter.remove(stateEventName)
    setViewState(update)
}

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
fun MainViewModel.clearUserList() {
    val update = getCurrentViewStateOrNew()
    update.fragmentUserList.arrUserList = null
    setViewState(update)
}

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
fun MainViewModel.setLayoutManagerState(layoutManagerState: Parcelable) {
    val update = getCurrentViewStateOrNew()
    update.fragmentUserList.layoutManagerState = layoutManagerState
    setViewState(update)
}

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
fun MainViewModel.clearLayoutManagerState() {
    val update = getCurrentViewStateOrNew()
    update.fragmentUserList.layoutManagerState = null
    setViewState(update)
}

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
fun MainViewModel.appendErrorState(errorState: ErrorState) {
    errorStack.add(errorState)
    printLogD(CLASS_NAME, "Appending error state. stack size: ${errorStack.size}")
}

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
fun MainViewModel.clearError(index: Int) {
    errorStack.removeAt(index)
}


@ExperimentalCoroutinesApi
@InternalCoroutinesApi
fun MainViewModel.setErrorStack(errorStack: ErrorStack) {
    this.errorStack.addAll(errorStack)
}









