package com.ravimhzn.sampletestandroidapplication.repository

import androidx.lifecycle.LiveData
import com.ravimhzn.sampletestandroidapplication.ui.DataState
import com.ravimhzn.sampletestandroidapplication.ui.state.MainViewState

interface MainRepository {

    fun getUserListFromServer(): LiveData<DataState<MainViewState>>

    fun getPhotoAlbumFromServer(id: Int): LiveData<DataState<MainViewState>>
}