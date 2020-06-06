package com.ravimhzn.sampletestandroidapplication.flows_coroutine.repository

import com.ravimhzn.sampletestandroidapplication.flows_coroutine.api.ApiServiceTest
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.ui.viewmodels.state.MainViewStateTest
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.util.ApiResponseHandler
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.util.DataStateTest
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.util.StateEvent
import com.ravimhzn.sampletestandroidapplication.network.responses.UserListResponse
import com.ravimhzn.sampletestandroidapplication.ui.DataState
import com.ravimhzn.sampletestandroidapplication.ui.state.MainViewState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryTestImpl @Inject constructor(
    private val apiService: ApiServiceTest
) : MainRepositoryTest {
    override fun getUserListFromServer(stateEvent: StateEvent): Flow<DataStateTest<MainViewStateTest>> =
        flow {
            val response = safeApiCall(IO) { apiService.getUserList() }

            val result = object: ApiResponseHandler<MainViewStateTest, List<UserListResponse>>(
                response = response,
                stateEvent = stateEvent
            ){
                override fun handleSuccess(resultObj: List<UserListResponse>): DataStateTest<MainViewStateTest> {
                    return DataStateTest.data(
                        data = MainViewStateTest(
                            fragmentUserList = MainViewStateTest.FragmentUserList(
                                arrUserList = resultObj
                            )
                        ),
                        stateEvent = stateEvent
                    )
                }
            }.getResult

            emit(result)
        }
}