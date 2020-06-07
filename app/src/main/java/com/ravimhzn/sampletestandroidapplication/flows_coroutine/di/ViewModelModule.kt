package com.ravimhzn.sampletestandroidapplication.flows_coroutine.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ravimhzn.sampletestandroidapplication.flows_coroutine.ui.viewmodels.MainViewModelTest
import com.ravimhzn.sampletestandroidapplication.ui.viewModels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

/* Alternative is provided for test (TestViewModelModule) */
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(vmFactory: MainViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @MainViewModelKey(MainViewModelTest::class)
    abstract fun bindMainViewModel(viewModel: MainViewModelTest): ViewModel

}














