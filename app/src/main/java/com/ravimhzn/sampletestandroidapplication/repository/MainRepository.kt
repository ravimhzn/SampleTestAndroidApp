package com.ravimhzn.sampletestandroidapplication.repository

import com.ravimhzn.sampletestandroidapplication.network.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) : JobManager("MainRepository") {}