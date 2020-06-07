package com.ravimhzn.sampletestandroidapplication.ui.state

import com.ravimhzn.sampletestandroidapplication.flows_coroutine.model.AlbumListResponse
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.model.UserListResponse
import com.ravimhzn.sampletestandroidapplication.ui.viewModels.MainViewModel

fun MainViewModel.setUserList(userList: MainViewState.UserList) {
    val update = getCurrentViewStateOrNew()
    if (update.userList == userList) {
        return
    }
    update.userList = userList
    setViewState(update)
}

fun MainViewModel.setPhotoAlbumList(photoAlbumnList: MainViewState.PhotoAlbumnList) {
    val update = getCurrentViewStateOrNew()
    if (update.photoAlbumnList == photoAlbumnList) {
        return
    }
    update.photoAlbumnList = photoAlbumnList
    setViewState(update)
}

fun MainViewModel.setUserListResponse(userListResponse: UserListResponse) {
    val update = getCurrentViewStateOrNew()
    update.photoAlbumnList.userListResponse = userListResponse
    setViewState(update)
}

fun MainViewModel.setAlbumListResponse(albumListResponse: AlbumListResponse) {
    val update = getCurrentViewStateOrNew()
    update.viewPhotoDetails.albumListResponse = albumListResponse
    setViewState(update)
}


fun MainViewModel.setViewPhotoDetails(viewPhotoDetails: MainViewState.ViewPhotoDetails) {
    val update = getCurrentViewStateOrNew()
    if (update.viewPhotoDetails == viewPhotoDetails) {
        return
    }
    update.viewPhotoDetails = viewPhotoDetails
    setViewState(update)
}