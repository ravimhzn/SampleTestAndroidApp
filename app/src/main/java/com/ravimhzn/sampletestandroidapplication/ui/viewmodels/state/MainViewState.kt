package com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state

import android.os.Parcelable
import com.ravimhzn.sampletestandroidapplication.model.AlbumListResponse
import com.ravimhzn.sampletestandroidapplication.model.UserListResponse
import kotlinx.android.parcel.Parcelize

const val MAIN_VIEW_STATE_BUNDLE_KEY =
    "com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state.MainViewState"

@Parcelize
data class MainViewState(

    var activeJobCounter: HashSet<String> = HashSet(),

    var fragmentUserList: FragmentUserList = FragmentUserList(),

    var fragmentPictureList: FragmentPictureList = FragmentPictureList()

) : Parcelable {

    @Parcelize
    data class FragmentUserList(
        var arrUserList: List<UserListResponse>? = null,
        var userListResponse: UserListResponse? = null,
        var layoutManagerState: Parcelable? = null
    ) : Parcelable

    @Parcelize
    data class FragmentPictureList(
        var arrAlbumListResponse: List<AlbumListResponse>? = null,
        var albumListResponse: AlbumListResponse? = null,
        var layoutManagerState: Parcelable? = null
    ) : Parcelable

}