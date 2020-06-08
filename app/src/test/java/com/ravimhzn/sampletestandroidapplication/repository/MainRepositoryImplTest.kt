package com.ravimhzn.sampletestandroidapplication.repository

import com.ravimhzn.sampletestandroidapplication.FakeApiService
import com.ravimhzn.sampletestandroidapplication.model.AlbumListResponse
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state.MainStateEvent
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state.MainViewState
import com.ravimhzn.sampletestandroidapplication.utils.DataState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.random.Random

@InternalCoroutinesApi
class MainRepositoryImplTest {

    private var SUT: MainRepositoryImpl

    private lateinit var apiService: FakeApiService

    init {
        this.javaClass.classLoader?.let { classLoader ->
            apiService = FakeApiService(classLoader)
        }
        SUT = MainRepositoryImpl(apiService)
    }

    @Test
    fun getUserList_success() {
        runBlocking {
            var results = 0

            //listen for flow emission
            SUT.getUserListFromServer(stateEvent = MainStateEvent.GetUserListEvent())
                .collect(object : FlowCollector<DataState<MainViewState>> {
                    override suspend fun emit(value: DataState<MainViewState>) {
                        value?.data?.fragmentUserList?.let { userList ->
                            results = userList.arrUserList?.size!!
                        }
                    }
                })

            //Compare with the number of notes in the fake data
            assertEquals(results, 10)
        }
    }

    @Test
    fun getPictureListFromServer_success() = runBlocking {
        //Generate random_user_id
        val randomUserId = Random.nextInt(1, 10)
        var results: List<AlbumListResponse>? = null

        //listen for success emission from flow
        SUT.getPictureListFromServer(
            stateEvent = MainStateEvent.GetUserListEvent(),
            id = randomUserId
        )
            .collect(object : FlowCollector<DataState<MainViewState>> {
                override suspend fun emit(value: DataState<MainViewState>) {
                    value?.data?.fragmentPictureList?.let { pictureList ->
                        results = pictureList.arrAlbumListResponse
                    }
                }
            })

        //confirm albumlist was retrieved
        assertTrue { results != null }

        //confirm the size of results with fake data
        assertEquals(results?.size, 50)
    }

    @Test
    fun getFilteredResponseTest() {
        runBlocking {
            val rawValue: List<AlbumListResponse> = apiService.getPhotoAlbum()
            val randomUserId = Random.nextInt(1, 10)
            val result: List<AlbumListResponse>? = SUT.getFilteredResponse(
                rawResponse = rawValue,
                id = randomUserId
            )

            assertTrue { result != null }

            assertTrue { result?.let { rawValue?.containsAll(it) } ?: false }
        }
    }

}