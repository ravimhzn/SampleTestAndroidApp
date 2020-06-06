package com.ravimhzn.sampletestandroidapplication.repository

import com.ravimhzn.sampletestandroidapplication.ui.DataState
import com.ravimhzn.sampletestandroidapplication.ui.state.MainViewState
import kotlinx.coroutines.flow.Flow

interface MainRepositoryTest {

    fun getUserListFromServer(): Flow<DataState<MainViewState>>

    fun getPhotoAlbumFromServer(id: Int): Flow<DataState<MainViewState>>
}