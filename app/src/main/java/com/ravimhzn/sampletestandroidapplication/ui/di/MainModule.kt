package com.ravimhzn.sampletestandroidapplication.ui.di

import com.ravimhzn.sampletestandroidapplication.network.ApiService
import com.ravimhzn.sampletestandroidapplication.repository.MainRepository
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
    ): MainRepository = MainRepository(apiService = apiService, connection = connection)
}