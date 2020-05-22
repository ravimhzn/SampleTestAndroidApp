package com.ravimhzn.sampletestandroidapplication.network.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AlbumListResponse(
    @SerializedName("albumId")
    @Expose
    val albumId: Int? = null,

    @SerializedName("id")
    @Expose
    val id: Int? = null,

    @SerializedName("thumbnailUrl")
    @Expose
    val thumbnailUrl: String? = null,

    @SerializedName("title")
    @Expose
    val title: String? = null,

    @SerializedName("url")
    @Expose
    val url: String? = null
)