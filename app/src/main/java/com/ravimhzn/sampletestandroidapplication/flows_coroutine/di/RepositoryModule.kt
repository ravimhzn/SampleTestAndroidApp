package com.ravimhzn.sampletestandroidapplication.flows_coroutine.di


import com.ravimhzn.sampletestandroidapplication.flows_coroutine.api.ApiServiceTest
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.repository.MainRepositoryTest
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.repository.MainRepositoryTestImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object RepositoryModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideApiService(retrofitBuilder: Retrofit.Builder): ApiServiceTest {
        return retrofitBuilder
            .build()
            .create(ApiServiceTest::class.java)
    }


    @JvmStatic
    @Singleton
    @Provides
    fun provideMainRepository(apiService: ApiServiceTest): MainRepositoryTest {
        return MainRepositoryTestImpl(apiService)
    }
}















