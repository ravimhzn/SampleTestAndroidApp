package com.ravimhzn.mightyapp.di.main

import androidx.lifecycle.ViewModel
import com.ravimhzn.mightyapp.di.ViewModelKey
import com.ravimhzn.mightyapp.ui.main.account.AccountViewModel
import com.ravimhzn.mightyapp.ui.main.blog.viewmodels.BlogViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AccountViewModel::class)
    abstract fun bindAccountViewModel(accountViewModel: AccountViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BlogViewModel::class)
    abstract fun bingBlogViewModel(blogViewModel: BlogViewModel): ViewModel
}