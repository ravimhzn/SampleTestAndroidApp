package com.ravimhzn.sampletestandroidapplication.flows_coroutine.api

import androidx.lifecycle.LiveData
import com.ravimhzn.sampletestandroidapplication.network.responses.AlbumListResponse
import com.ravimhzn.sampletestandroidapplication.network.responses.UserListResponse
import com.ravimhzn.sampletestandroidapplication.utils.Constants
import com.ravimhzn.sampletestandroidapplication.utils.GenericApiResponse
import org.intellij.lang.annotations.Flow
import retrofit2.http.GET

interface ApiServiceTest {

    @GET(Constants.PREFIX_USERS)
    suspend fun getUserList(): List<UserListResponse>

    @GET(Constants.PREFIX_PHOTOS)
    suspend fun getPhotoAlbum(): List<AlbumListResponse>
}