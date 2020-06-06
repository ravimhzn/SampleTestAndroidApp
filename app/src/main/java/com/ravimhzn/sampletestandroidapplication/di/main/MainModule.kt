package com.ravimhzn.sampletestandroidapplication.di.main

import com.ravimhzn.sampletestandroidapplication.flows_coroutine.api.ApiServiceTest
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.repository.MainRepositoryTest
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.repository.MainRepositoryTestImpl
import com.ravimhzn.sampletestandroidapplication.network.ApiService
import com.ravimhzn.sampletestandroidapplication.repository.MainRepositoryImpl
import com.ravimhzn.sampletestandroidapplication.utils.Connection
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideApiService(retrofitBuilder: Retrofit.Builder): ApiService =
        retrofitBuilder.build()
            .create(ApiService::class.java)

    @MainScope
    @Provides
    fun provideMainRepository(
        apiService: ApiService,
        connection: Connection
    ): MainRepositoryImpl = MainRepositoryImpl(apiService = apiService, connection = connection)

    @MainScope
    @Provides
    fun provideMainRepositoryTest(
        apiService: ApiServiceTest,
        connection: Connection
    ): MainRepositoryTest = MainRepositoryTestImpl(apiService = apiService)
}