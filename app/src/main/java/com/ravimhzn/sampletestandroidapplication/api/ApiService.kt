package com.ravimhzn.sampletestandroidapplication.api

import com.ravimhzn.sampletestandroidapplication.model.AlbumListResponse
import com.ravimhzn.sampletestandroidapplication.model.UserListResponse
import retrofit2.http.GET

interface ApiService {

    @GET(PREFIX_USERS)
    suspend fun getUserList(): List<UserListResponse>

    @GET(PREFIX_PHOTOS)
    suspend fun getPhotoAlbum(): List<AlbumListResponse>

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
        const val PREFIX_USERS = "/users"
        const val PREFIX_PHOTOS = "/photos"
    }
}