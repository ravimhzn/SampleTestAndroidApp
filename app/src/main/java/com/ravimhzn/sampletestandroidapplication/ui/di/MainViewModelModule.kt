package com.ravimhzn.sampletestandroidapplication.ui.di

import androidx.lifecycle.ViewModel
import com.ravimhzn.sampletestandroidapplication.di.ViewModelKey
import com.ravimhzn.sampletestandroidapplication.ui.viewModels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindUserViewModel(fragmentUserViewModel: MainViewModel): ViewModel
}