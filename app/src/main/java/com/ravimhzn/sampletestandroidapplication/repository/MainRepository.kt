package com.ravimhzn.sampletestandroidapplication.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.ravimhzn.sampletestandroidapplication.network.ApiService
import com.ravimhzn.sampletestandroidapplication.network.responses.UserListResponse
import com.ravimhzn.sampletestandroidapplication.ui.DataState
import com.ravimhzn.sampletestandroidapplication.ui.state.MainViewState
import com.ravimhzn.sampletestandroidapplication.utils.ApiSuccessResponse
import com.ravimhzn.sampletestandroidapplication.utils.Connection
import com.ravimhzn.sampletestandroidapplication.utils.GenericApiResponse
import kotlinx.coroutines.Job
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val connection: Connection
) : JobManager("MainRepository") {

    private val TAG = "AppDebug ->"

    fun getUserListFromServer(): LiveData<DataState<MainViewState>> {
        return object : NetworkBoundResource<List<UserListResponse>, MainViewState>(
            connection.isConnectedToInternet(),
            true,
            true
        ) {
            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<List<UserListResponse>>) {
                Log.d(TAG, "handleApiSuccessResponse: $response")
                onCompleteJob(
                    DataState.data(
                        MainViewState(
                            userList = MainViewState.UserList(
                                arrUserList = response.body
                            )
                        )
                    )
                )
            }

            override fun createCall(): LiveData<GenericApiResponse<List<UserListResponse>>> {
                return apiService.getUserList()
            }

            override fun setJob(job: Job) {
                addJob("getUserListFromServer", job)
            }

        }.asLiveData()
    }

}