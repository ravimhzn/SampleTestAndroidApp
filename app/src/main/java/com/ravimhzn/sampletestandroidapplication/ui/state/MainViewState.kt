package com.ravimhzn.sampletestandroidapplication.ui.state

import com.ravimhzn.sampletestandroidapplication.network.responses.AlbumListResponse
import com.ravimhzn.sampletestandroidapplication.network.responses.UserListResponse

data class MainViewState(
    var userList: UserList = UserList(),
    var photoAlbumnList: PhotoAlbumnList = PhotoAlbumnList(),
    var viewPhotoDetails: ViewPhotoDetails = ViewPhotoDetails()
) {
    data class UserList(
        var arrUserList: List<UserListResponse> = ArrayList<UserListResponse>()
    )

    data class PhotoAlbumnList(
        var userListResponse: UserListResponse? = null,
        var arrPhotoAlbum: List<AlbumListResponse> = ArrayList<AlbumListResponse>()//to get the list from server
    )

    data class ViewPhotoDetails(
        var albumListResponse: AlbumListResponse? = null
    )
}