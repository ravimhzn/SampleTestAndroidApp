package com.ravimhzn.sampletestandroidapplication

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ravimhzn.sampletestandroidapplication.api.ApiService
import com.ravimhzn.sampletestandroidapplication.model.AlbumListResponse
import com.ravimhzn.sampletestandroidapplication.model.UserListResponse

class FakeApiService(
    private val testClassLoader: ClassLoader
) : ApiService {

    override suspend fun getUserList(): List<UserListResponse> {
        val userList: List<UserListResponse> = Gson().fromJson(
            getNotesFromFile("userlist.json"),
            object : TypeToken<List<UserListResponse>>() {}.type
        )
        return userList
    }

    override suspend fun getPhotoAlbum(): List<AlbumListResponse> {
        val albumListResponse: List<AlbumListResponse> = Gson().fromJson(
            getNotesFromFile("picturelist.json"),
            object : TypeToken<List<AlbumListResponse>>() {}.type
        )
        return albumListResponse
    }

    fun getNotesFromFile(fileName: String): String {
        return testClassLoader.getResource(fileName).readText() //easy way to read local json files
    }

}