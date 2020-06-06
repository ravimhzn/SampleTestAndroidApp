package com.ravimhzn.sampletestandroidapplication.di.main

import androidx.lifecycle.ViewModel
import com.ravimhzn.sampletestandroidapplication.di.ViewModelKey
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.ui.viewmodels.MainViewModelTest
import com.ravimhzn.sampletestandroidapplication.ui.viewModels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.InternalCoroutinesApi

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindUserViewModel(fragmentUserViewModel: MainViewModel): ViewModel

    @InternalCoroutinesApi
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModelTest::class)
    abstract fun bindUserViewModelTest(fragmentUserViewModel: MainViewModelTest): ViewModel
}