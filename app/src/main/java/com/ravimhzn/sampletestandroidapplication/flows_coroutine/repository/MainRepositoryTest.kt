package com.ravimhzn.sampletestandroidapplication.flows_coroutine.repository

import com.ravimhzn.sampletestandroidapplication.flows_coroutine.ui.viewmodels.state.MainViewStateTest
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.util.DataStateTest
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.util.StateEvent
import com.ravimhzn.sampletestandroidapplication.ui.DataState
import com.ravimhzn.sampletestandroidapplication.ui.state.MainViewState
import kotlinx.coroutines.flow.Flow

interface MainRepositoryTest {

    fun getUserListFromServer(stateEvent: StateEvent): Flow<DataStateTest<MainViewStateTest>>
}