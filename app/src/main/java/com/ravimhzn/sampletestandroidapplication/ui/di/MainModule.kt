package com.ravimhzn.mightyapp.di.main

import com.ravimhzn.mightyapp.network.main.OpenApiMainService
import com.ravimhzn.mightyapp.persistence.AccountPropertiesDao
import com.ravimhzn.mightyapp.persistence.AppDatabase
import com.ravimhzn.mightyapp.persistence.BlogPostDao
import com.ravimhzn.mightyapp.repository.main.AccountRepository
import com.ravimhzn.mightyapp.repository.main.BlogRepository
import com.ravimhzn.mightyapp.session.SessionManager
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @MainScope
    @Provides
    fun provideApiService(retrofitBuilder: Retrofit.Builder): OpenApiMainService =
        retrofitBuilder.build()
            .create(OpenApiMainService::class.java)

    @MainScope
    @Provides
    fun provideAccountRepository(
        openApiMainService: OpenApiMainService,
        accountPropertiesDao: AccountPropertiesDao,
        sessionManager: SessionManager
    ): AccountRepository =
        AccountRepository(
            openApiMainService,
            accountPropertiesDao,
            sessionManager
        )

    @MainScope
    @Provides
    fun provideBlogPostDao(db: AppDatabase): BlogPostDao {
        return db.getBlogPostDao()
    }

    @MainScope
    @Provides
    fun provideBlogRepository(
        openApiMainService: OpenApiMainService,
        blogPostDao: BlogPostDao,
        sessionManager: SessionManager
    ): BlogRepository {
        return BlogRepository(
            openApiMainService = openApiMainService,
            blogPostDao = blogPostDao,
            sessionManager = sessionManager
        )
    }

}