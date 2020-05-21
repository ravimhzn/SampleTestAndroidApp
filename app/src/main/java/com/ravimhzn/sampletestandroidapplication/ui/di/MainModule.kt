package com.ravimhzn.sampletestandroidapplication.ui.di

import com.ravimhzn.sampletestandroidapplication.network.ApiService
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

//    @MainScope
//    @Provides
//    fun provideAccountRepository(
//        openApiMainService: OpenApiMainService,
//        accountPropertiesDao: AccountPropertiesDao,
//        sessionManager: SessionManager
//    ): AccountRepository =
//        AccountRepository(
//            openApiMainService,
//            accountPropertiesDao,
//            sessionManager
//        )
}