package com.ravimhzn.sampletestandroidapplication.network.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserListResponse : ArrayList<UserListResponseItem>()

data class UserListResponseItem(
    @SerializedName("email")
    @Expose
    val email: String? = null,

    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("phone")
    @Expose
    val phone: String? = null
)