package com.ravimhzn.sampletestandroidapplication.ui.state

import com.ravimhzn.sampletestandroidapplication.network.responses.UserListResponse

data class MainViewState(
    var userList: UserList = UserList()
) {
    data class UserList(
        var arrUserList: List<UserListResponse> = ArrayList<UserListResponse>()
    )
}