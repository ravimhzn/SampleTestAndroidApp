package com.ravimhzn.sampletestandroidapplication.network

import androidx.lifecycle.LiveData
import com.ravimhzn.sampletestandroidapplication.network.responses.UserListResponse
import com.ravimhzn.sampletestandroidapplication.utils.Constants.Companion.PREFIX_USERS
import com.ravimhzn.sampletestandroidapplication.utils.GenericApiResponse
import retrofit2.http.GET

interface ApiService {

    @GET(PREFIX_USERS)
    fun getUserList(): LiveData<GenericApiResponse<UserListResponse>>


}