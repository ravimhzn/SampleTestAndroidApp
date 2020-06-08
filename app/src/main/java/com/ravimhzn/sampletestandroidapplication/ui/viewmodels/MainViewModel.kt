package com.ravimhzn.sampletestandroidapplication.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.espressodaggerexamples.ui.viewmodel.isJobAlreadyActive
import com.ravimhzn.sampletestandroidapplication.repository.MainRepository
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state.MainStateEvent
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state.MainStateEvent.GetPictureList
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state.MainStateEvent.GetUserListEvent
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state.MainViewState
import com.ravimhzn.sampletestandroidapplication.utils.Constants.Companion.TAG
import com.ravimhzn.sampletestandroidapplication.utils.DataState
import com.ravimhzn.sampletestandroidapplication.utils.ErrorStack
import com.ravimhzn.sampletestandroidapplication.utils.ErrorState
import com.ravimhzn.sampletestandroidapplication.utils.StateEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@UseExperimental(FlowPreview::class)
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class MainViewModel
@Inject
constructor(
    private val mainRepository: MainRepository
) : ViewModel() {


    val CLASS_NAME = "MainViewModel"

    private val dataChannel = ConflatedBroadcastChannel<DataState<MainViewState>>()

    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val errorStack = ErrorStack()

    val errorState: LiveData<ErrorState> = errorStack.errorState

    val viewState: LiveData<MainViewState>
        get() = _viewState

    init {
        setupChannel()
    }

    private fun setupChannel() {
        dataChannel
            .asFlow()
            .onEach { dataState ->
                dataState.data?.let { data ->
                    handleNewData(dataState.stateEvent, data)
                }
                dataState.error?.let { error ->
                    handleNewError(dataState.stateEvent, error)
                }
            }
            .launchIn(viewModelScope)
    }

    private fun offerToDataChannel(dataState: DataState<MainViewState>) {
        if (!dataChannel.isClosedForSend) {
            dataChannel.offer(dataState)
        }
    }

    fun setStateEvent(stateEvent: MainStateEvent) {
        when (stateEvent) {

            is GetUserListEvent -> {
                launchJob(
                    stateEvent = stateEvent,
                    jobFunction = mainRepository.getUserListFromServer(stateEvent)
                )
            }

            is GetPictureList -> {
                Log.d(TAG, "testID: ${stateEvent.id}")
                launchJob(
                    stateEvent = stateEvent,
                    jobFunction = mainRepository.getPictureListFromServer(stateEvent, stateEvent.id)
                )
            }

        }
    }

    private fun handleNewError(stateEvent: StateEvent, error: ErrorState) {
        appendErrorState(error)
        removeJobFromCounter(stateEvent.toString())
    }

    fun handleNewData(stateEvent: StateEvent, data: MainViewState) {

        data.fragmentUserList.arrUserList?.let { userListResponse ->
            setUserList(userListResponse)
        }

        data.fragmentPictureList.arrAlbumListResponse?.let { list ->
            setPictureList(list)
        }

        removeJobFromCounter(stateEvent.toString()) //This will disable progressbar
    }

    private fun launchJob(stateEvent: StateEvent, jobFunction: Flow<DataState<MainViewState>>) {
        if (!isJobAlreadyActive(stateEvent.toString())) {
            addJobToCounter(stateEvent.toString())
            jobFunction
                .onEach { dataState ->
                    offerToDataChannel(dataState)
                }
                .launchIn(viewModelScope)
        }
    }

    fun setViewState(viewState: MainViewState) {
        _viewState.value = viewState
    }
}