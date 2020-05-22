package com.ravimhzn.sampletestandroidapplication.ui.viewModels

import androidx.lifecycle.LiveData
import com.ravimhzn.sampletestandroidapplication.network.responses.UserListResponse
import com.ravimhzn.sampletestandroidapplication.repository.MainRepository
import com.ravimhzn.sampletestandroidapplication.ui.BaseViewModel
import com.ravimhzn.sampletestandroidapplication.ui.DataState
import com.ravimhzn.sampletestandroidapplication.ui.state.MainStateEvent
import com.ravimhzn.sampletestandroidapplication.ui.state.MainStateEvent.*
import com.ravimhzn.sampletestandroidapplication.ui.state.MainViewState
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel<MainStateEvent, MainViewState>() {

    override fun initNewViewState(): MainViewState {
        return MainViewState()
    }

    override fun handleStateEvent(stateEvent: MainStateEvent): LiveData<DataState<MainViewState>> {
        return when (stateEvent) {
            is GetUserListEvent -> {
                mainRepository.getUserListFromServer()
            }
            is GetPhotoAlbumListEvent -> {
                mainRepository.getPhotoAlbumFromServer(stateEvent.id)
            }
            is None -> {
                /**
                 * have to send null data otherwise it will crash on navigation. U can try and delete this code inside here
                 * None is called whenever a new fragment comes into view. It is so to cancel any active/ pending jobs.
                 */
                object : LiveData<DataState<MainViewState>>() {
                    override fun onActive() {
                        super.onActive()
                        value = DataState.data(null, null)
                    }
                }
            }
        }
    }


    fun setUserList(userList: MainViewState.UserList) {
        val update = getCurrentViewStateOrNew()
        if (update.userList == userList) {
            return
        }
        update.userList = userList
        setViewState(update)
    }


    fun setPhotoAlbumList(photoAlbumnList: MainViewState.PhotoAlbumnList) {
        val update = getCurrentViewStateOrNew()
        if (update.photoAlbumnList == photoAlbumnList) {
            return
        }
        update.photoAlbumnList = photoAlbumnList
        setViewState(update)
    }

    fun setUserListResponse(userListResponse: UserListResponse) {
        val update = getCurrentViewStateOrNew()
        update.photoAlbumnList.userListResponse = userListResponse
        setViewState(update)
    }
//
//    fun setPhotoAlbumList(arrAlbumListResponse: List<AlbumListResponse>) {
//        val update = getCurrentViewStateOrNew()
//        if (update.photoAlbumnList == arrAlbumListResponse) {
//            return
//        }
//        update.photoAlbumnList.arrPhotoAlbum = arrAlbumListResponse
//        setViewState(update)
//    }

    fun cancelActiveJobs() {
        mainRepository.cancelActiveJobs()
        handlePendingData()
    }

    private fun handlePendingData() {
        setStateEvent(None())
    }

    override fun onCleared() {
        super.onCleared()
        cancelActiveJobs()
    }


}