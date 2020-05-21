package com.ravimhzn.mightyapp.di.main

import com.ravimhzn.mightyapp.ui.main.account.AccountFragment
import com.ravimhzn.mightyapp.ui.main.account.ChangePasswordFragment
import com.ravimhzn.mightyapp.ui.main.account.UpdateAccountFragment
import com.ravimhzn.mightyapp.ui.main.blog.BlogFragment
import com.ravimhzn.mightyapp.ui.main.blog.UpdateBlogFragment
import com.ravimhzn.mightyapp.ui.main.blog.ViewBlogFragment
import com.ravimhzn.mightyapp.ui.main.create_blog.CreateBlogFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {
    
    @ContributesAndroidInjector()
    abstract fun contributeBlogFragment(): BlogFragment

    @ContributesAndroidInjector()
    abstract fun contributeAccountFragment(): AccountFragment

    @ContributesAndroidInjector()
    abstract fun contributeChangePasswordFragment(): ChangePasswordFragment

    @ContributesAndroidInjector()
    abstract fun contributeCreateBlogFragment(): CreateBlogFragment

    @ContributesAndroidInjector()
    abstract fun contributeUpdateBlogFragment(): UpdateBlogFragment

    @ContributesAndroidInjector()
    abstract fun contributeViewBlogFragment(): ViewBlogFragment

    @ContributesAndroidInjector()
    abstract fun contributeUpdateAccountFragment(): UpdateAccountFragment
}