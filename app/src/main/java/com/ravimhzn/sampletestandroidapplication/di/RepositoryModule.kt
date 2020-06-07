package com.ravimhzn.sampletestandroidapplication.di


import com.ravimhzn.sampletestandroidapplication.api.ApiService
import com.ravimhzn.sampletestandroidapplication.repository.MainRepositoryTest
import com.ravimhzn.sampletestandroidapplication.repository.MainRepositoryTestImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
object RepositoryModule {

    @JvmStatic
    @Singleton
    @Provides
    fun provideApiService(retrofitBuilder: Retrofit.Builder): ApiService {
        return retrofitBuilder
            .build()
            .create(ApiService::class.java)
    }


    @JvmStatic
    @Singleton
    @Provides
    fun provideMainRepository(apiService: ApiService): MainRepositoryTest {
        return MainRepositoryTestImpl(
            apiService
        )
    }
}















