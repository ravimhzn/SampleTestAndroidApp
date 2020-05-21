package com.ravimhzn.sampletestandroidapplication.ui.viewModels

import androidx.lifecycle.LiveData
import com.ravimhzn.sampletestandroidapplication.repository.MainRepository
import com.ravimhzn.sampletestandroidapplication.ui.BaseViewModel
import com.ravimhzn.sampletestandroidapplication.ui.DataState
import com.ravimhzn.sampletestandroidapplication.ui.state.MainStateEvent
import com.ravimhzn.sampletestandroidapplication.ui.state.MainStateEvent.GetUserListEvent
import com.ravimhzn.sampletestandroidapplication.ui.state.MainStateEvent.None
import com.ravimhzn.sampletestandroidapplication.ui.state.MainViewState
import com.ravimhzn.sampletestandroidapplication.utils.AbsentLiveData
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
            is None -> {
                AbsentLiveData.create()
            }
        }
    }

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