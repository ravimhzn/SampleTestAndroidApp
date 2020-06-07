package com.ravimhzn.sampletestandroidapplication.flows_coroutine.ui.viewmodels

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.repository.MainRepositoryTest
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.ui.viewmodels.state.MainStateEventTest
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.ui.viewmodels.state.MainStateEventTest.GetUserListEvent
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.ui.viewmodels.state.MainViewStateTest
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.util.*
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.model.UserListResponse
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
class MainViewModelTest
@Inject
constructor(
    private val mainRepository: MainRepositoryTest
) : ViewModel() {

    val CLASS_NAME = "MainViewModel"

    private val dataChannel = ConflatedBroadcastChannel<DataStateTest<MainViewStateTest>>()

    val errorStack = ErrorStack()

    val errorState: LiveData<ErrorState> = errorStack.errorState

    private val _viewState: MutableLiveData<MainViewStateTest> = MutableLiveData()

    val viewState: LiveData<MainViewStateTest>
        get() = _viewState

    init {
        setUpChannel()
    }

    private fun setUpChannel() {
        dataChannel
            .asFlow()
            .onEach { dataState ->
                dataState.data?.let { mainViewState ->
                    handleNewData(dataState.stateEvent, mainViewState)
                }

                dataState.error?.let { error ->
                    handleNewError(dataState.stateEvent, error)
                }
            }
            .launchIn(viewModelScope)
    }

    fun setStateEvent(stateEvent: MainStateEventTest) {
        when (stateEvent) {
            is GetUserListEvent -> {
                launchJob(
                    stateEvent = stateEvent,
                    jobFunction = mainRepository.getUserListFromServer(stateEvent)
                )
            }
        }
    }

    private fun handleNewError(stateEvent: StateEvent, error: ErrorState) {
        appendErrorState(error)
        removeJobFromCounter(stateEvent.toString())
    }

    private fun handleNewData(stateEvent: StateEvent, mainViewState: MainViewStateTest) {
        mainViewState.fragmentUserList.arrUserList?.let { userList ->
            setUserListData(userList)
        }
    }

    //Setters
    fun setViewState(viewState: MainViewStateTest) {
        _viewState.value = viewState
    }

    private fun setUserListData(userList: List<UserListResponse>) {
        val update = getCurrentViewStateOrNew()
        update.fragmentUserList.arrUserList = userList
        setViewState(update)
    }

    fun setLayoutManagerState(layoutManagerState: Parcelable) {
        val update = getCurrentViewStateOrNew()
        update.fragmentUserList.layoutManagerState = layoutManagerState
        setViewState(update)
    }

    fun clearLayoutManagerState() {
        val update = getCurrentViewStateOrNew()
        update.fragmentUserList.layoutManagerState = null
        setViewState(update)
    }

    fun setErrorStack(errorStack: ErrorStack) {
        this.errorStack.addAll(errorStack)
    }

    fun clearError(index: Int) {
        errorStack.removeAt(index)
    }

    fun appendErrorState(errorState: ErrorState) {
        errorStack.add(errorState)
        printLogD(CLASS_NAME, "Appending error state. stack size: ${errorStack.size}")
    }

    fun removeJobFromCounter(stateEventName: String) {
        val update = getCurrentViewStateOrNew()
        update.activeJobCounter.remove(stateEventName)
        setViewState(update)
    }

    //Getters
    fun getCurrentViewStateOrNew(): MainViewStateTest {
        val value = viewState.value?.let {
            it
        } ?: MainViewStateTest()
        return value
    }

    fun clearActiveJobCounter() {
        val update = getCurrentViewStateOrNew()
        update.activeJobCounter.clear()
        setViewState(update)
    }

    fun getLayoutManagerState(): Parcelable? {
        val viewState = getCurrentViewStateOrNew()
        return viewState.fragmentUserList.layoutManagerState
    }

    fun areAnyJobsActive(): Boolean {
        val viewState = getCurrentViewStateOrNew()
        return viewState.activeJobCounter.size > 0
    }

    //Supporting functions
    private fun launchJob(
        stateEvent: StateEvent,
        jobFunction: Flow<DataStateTest<MainViewStateTest>>
    ) {
        if (!isJobAlreadyActive(stateEvent.toString())) {
            addJobToCounter(stateEvent.toString())
            jobFunction
                .onEach { dataState ->
                    victimizeToDataChannel(dataState)
                }
                .launchIn(viewModelScope)
        }
    }

    fun victimizeToDataChannel(dataState: DataStateTest<MainViewStateTest>) {
        if (!dataChannel.isClosedForSend) {
            dataChannel.offer(dataState)
        }
    }

    fun addJobToCounter(stateEventName: String) {
        val update = getCurrentViewStateOrNew()
        update.activeJobCounter.add(stateEventName)
        setViewState(update)
    }

    fun isJobAlreadyActive(stateEventName: String): Boolean {
        val viewState = getCurrentViewStateOrNew()
        return viewState.activeJobCounter.contains(stateEventName)
    }
}