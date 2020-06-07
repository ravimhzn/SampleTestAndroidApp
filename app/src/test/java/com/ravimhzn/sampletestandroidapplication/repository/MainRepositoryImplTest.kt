package com.ravimhzn.sampletestandroidapplication.repository

import com.ravimhzn.sampletestandroidapplication.FakeApiService
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state.MainStateEvent
import com.ravimhzn.sampletestandroidapplication.ui.viewmodels.state.MainViewState
import com.ravimhzn.sampletestandroidapplication.utils.DataState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

@InternalCoroutinesApi
class MainRepositoryImplTest {

    private lateinit var SUT: MainRepositoryImpl

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
            SUT.getUserListFromServer(stateEvent = MainStateEvent.GetUserListEvent())
                .collect(object : FlowCollector<DataState<MainViewState>> {
                    override suspend fun emit(value: DataState<MainViewState>) {
                        value?.data?.fragmentUserList?.let { userList ->
                            results = userList.arrUserList?.size!!
                        }
                    }
                })

            //Compare with the number of notes in the fake data
            Assertions.assertEquals(results, 10)
        }
    }

}