package com.ravimhzn.sampletestandroidapplication.repository

import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state.MainViewState
import com.ravimhzn.sampletestandroidapplication.utils.DataState
import com.ravimhzn.sampletestandroidapplication.utils.StateEvent
import kotlinx.coroutines.flow.Flow

interface MainRepositoryTest {

    fun getUserListFromServer(stateEvent: StateEvent): Flow<DataState<MainViewState>>
}