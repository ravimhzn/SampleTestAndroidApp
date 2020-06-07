package com.ravimhzn.sampletestandroidapplication.flows_coroutine.ui.viewmodels.state

import android.os.Parcelable
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.model.UserListResponse
import kotlinx.android.parcel.Parcelize

const val MAIN_VIEW_STATE_BUNDLE_KEY =
    "com.ravimhzn.sampletestandroidapplication.flows_coroutine.ui.viewmodels.state.MainViewState"

@Parcelize
data class MainViewStateTest(

    var activeJobCounter: HashSet<String> = HashSet(),

    var fragmentUserList: FragmentUserList = FragmentUserList()

) : Parcelable {

    @Parcelize
    data class FragmentUserList(
        var arrUserList: List<UserListResponse>? = null,
        var layoutManagerState: Parcelable? = null
    ) : Parcelable

}