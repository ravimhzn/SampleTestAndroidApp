package com.ravimhzn.sampletestandroidapplication.z
//
//import android.util.Log
//import androidx.lifecycle.LiveData
//import com.ravimhzn.sampletestandroidapplication.network.ApiService
//import com.ravimhzn.sampletestandroidapplication.model.AlbumListResponse
//import com.ravimhzn.sampletestandroidapplication.model.UserListResponse
//import com.ravimhzn.sampletestandroidapplication.ui.state.MainViewState
//import com.ravimhzn.sampletestandroidapplication.utils.ApiSuccessResponse
//import com.ravimhzn.sampletestandroidapplication.utils.Connection
//import com.ravimhzn.sampletestandroidapplication.utils.GenericApiResponse
//import kotlinx.coroutines.InternalCoroutinesApi
//import kotlinx.coroutines.Job
//import javax.inject.Inject
//
//@UseExperimental(InternalCoroutinesApi::class)
//class MainRepositoryImpl @Inject constructor(
//    private val apiService: ApiService,
//    private val connection: Connection
//) : JobManager("MainRepository") {
//
//    private val TAG = "AppDebug ->"
//
//
//    fun getUserListFromServer(): LiveData<DataState<MainViewState>> {
//        return object : NetworkBoundResource<List<UserListResponse>, MainViewState>(
//            connection.isConnectedToInternet(),
//            true,
//            true
//        ) {
//            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<List<UserListResponse>>) {
//                Log.d(TAG, "handleApiSuccessResponse: $response")
//                onCompleteJob(
//                    DataState.data(
//                        MainViewState(
//                            userList = MainViewState.UserList(
//                                arrUserList = response.body
//                            )
//                        )
//                    )
//                )
//            }
//
//            override fun createCall(): LiveData<GenericApiResponse<List<UserListResponse>>> {
//                return apiService.getUserList()
//            }
//
//            override fun setJob(job: Job) {
//                addJob("getUserListFromServer", job)
//            }
//
//        }.asLiveData()
//    }
//
//    fun getPhotoAlbumFromServer(id: Int): LiveData<DataState<MainViewState>> {
//        return object : NetworkBoundResource<List<AlbumListResponse>, MainViewState>(
//            connection.isConnectedToInternet(),
//            isNetworkRequest = true,
//            shouldCancelIfNoInternet = true
//        ) {
//            override suspend fun handleApiSuccessResponse(response: ApiSuccessResponse<List<AlbumListResponse>>) {
//                Log.d(TAG, "handleApiSuccessResponse: $response")
//                var rawResponse = response.body
//                var filteredResponse = getFilteredResponse(rawResponse, id)
//                onCompleteJob(
//                    DataState.data(
//                        MainViewState(
//                            photoAlbumnList = MainViewState.PhotoAlbumnList(
//                                arrPhotoAlbum = filteredResponse
//                            )
//                        )
//                    )
//                )
//            }
//
//            private fun getFilteredResponse(
//                rawResponse: List<AlbumListResponse>,
//                id: Int
//            ): List<AlbumListResponse> {
//                return rawResponse.filter { albumListResponse ->
//                    checkIfIdMatches(albumListResponse, id)
//                }
//            }
//
//            private fun checkIfIdMatches(it: AlbumListResponse, id: Int): Boolean {
//                return it?.albumId == id
//            }
//
//            override fun createCall(): LiveData<GenericApiResponse<List<AlbumListResponse>>> {
//                return apiService.getPhotoAlbum()
//            }
//
//            override fun setJob(job: Job) {
//                addJob("getPhotoAlbumFromServer", job)
//            }
//        }.asLiveData()
//    }
//}