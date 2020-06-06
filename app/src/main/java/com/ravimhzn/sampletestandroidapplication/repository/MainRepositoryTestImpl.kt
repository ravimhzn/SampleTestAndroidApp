package com.ravimhzn.sampletestandroidapplication.repository

import com.ravimhzn.sampletestandroidapplication.network.ApiService
import com.ravimhzn.sampletestandroidapplication.ui.DataState
import com.ravimhzn.sampletestandroidapplication.ui.state.MainViewState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepositoryTestImpl @Inject constructor(
    private val apiService: ApiService
): MainRepositoryTest {

    /**
     *  return object : NetworkBoundResource<List<UserListResponse>, MainViewState>(
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
    )    )    )    )    }

    override fun createCall(): LiveData<GenericApiResponse<List<UserListResponse>>> {
    return apiService.getUserList()
    }

    override fun setJob(job: Job) {
    addJob("getUserListFromServer", job)
    }

    }.asLiveData()
     */

    override fun getUserListFromServer(): Flow<DataState<MainViewState>> {
        TODO("Not yet implemented")
    }

    override fun getPhotoAlbumFromServer(id: Int): Flow<DataState<MainViewState>> {
        TODO("Not yet implemented")
    }


}