package com.ravimhzn.sampletestandroidapplication.ui.state

import com.ravimhzn.sampletestandroidapplication.network.responses.UserListResponse

data class MainViewState(
    var userList: UserList = UserList()
//            TODO ("FOR PHOTOS")
) {
    data class UserList(
        var userList: UserListResponse = UserListResponse()
    )
}