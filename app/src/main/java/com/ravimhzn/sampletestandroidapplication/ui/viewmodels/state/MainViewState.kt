package com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state

import android.os.Parcelable
import com.ravimhzn.sampletestandroidapplication.model.UserListResponse
import kotlinx.android.parcel.Parcelize

const val MAIN_VIEW_STATE_BUNDLE_KEY =
    "com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state.MainViewState"

@Parcelize
data class MainViewState(

    var activeJobCounter: HashSet<String> = HashSet(),

    var fragmentUserList: FragmentUserList = FragmentUserList()

) : Parcelable {

    @Parcelize
    data class FragmentUserList(
        var arrUserList: List<UserListResponse>? = null,
        var layoutManagerState: Parcelable? = null
    ) : Parcelable

}