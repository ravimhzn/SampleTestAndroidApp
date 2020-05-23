package com.ravimhzn.sampletestandroidapplication.di


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ravimhzn.sampletestandroidapplication.utils.Constants.Companion.BASE_URL
import com.ravimhzn.sampletestandroidapplication.utils.LiveDataCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation()
        .create() //it will exclude fields that are not annotated with @Expose annotation


    @Singleton
    @Provides
    fun provideRetrofitBuilder(gson: Gson): Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create(gson))
}

