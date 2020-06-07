package com.ravimhzn.sampletestandroidapplication.flows_coroutine.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.api.ApiServiceTest
import com.ravimhzn.sampletestandroidapplication.utils.Constants
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * These are dependencies that are not provided to other parts of the application.
 * They are used 'internally' by other dependencies.
 */
/* **These will NOT be mocked in tests***/
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Module
object InternalBindingsModule {


    @JvmStatic
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson {
        return GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    }

    @JvmStatic
    @Singleton
    @Provides
    fun provideRetrofitBuilder(gsonBuilder: Gson): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(ApiServiceTest.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
    }
}













