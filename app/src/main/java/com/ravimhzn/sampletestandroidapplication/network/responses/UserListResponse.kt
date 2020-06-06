package com.ravimhzn.sampletestandroidapplication.network.responses

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserListResponse(
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
): Parcelable