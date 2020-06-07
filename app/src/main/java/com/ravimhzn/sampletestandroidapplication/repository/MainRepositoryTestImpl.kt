package com.ravimhzn.sampletestandroidapplication.repository

import com.ravimhzn.sampletestandroidapplication.api.ApiService
import com.ravimhzn.sampletestandroidapplication.model.UserListResponse
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state.MainViewState
import com.ravimhzn.sampletestandroidapplication.utils.ApiResponseHandler
import com.ravimhzn.sampletestandroidapplication.utils.DataState
import com.ravimhzn.sampletestandroidapplication.utils.StateEvent
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryTestImpl @Inject constructor(
    private val apiService: ApiService
) : MainRepositoryTest {
    override fun getUserListFromServer(stateEvent: StateEvent): Flow<DataState<MainViewState>> =
        flow {
            val response =
                safeApiCall(IO) {
                    apiService.getUserList()
                }

            emit(
                object : ApiResponseHandler<MainViewState, List<UserListResponse>>(
                    response = response,
                    stateEvent = stateEvent
                ) {
                    override fun handleSuccess(resultObj: List<UserListResponse>): DataState<MainViewState> {
                        return DataState.data(
                            data = MainViewState(
                                fragmentUserList = MainViewState.FragmentUserList(
                                    arrUserList = resultObj
                                )
                            ),
                            stateEvent = stateEvent
                        )
                    }
                }.getResult
            )
        }
}